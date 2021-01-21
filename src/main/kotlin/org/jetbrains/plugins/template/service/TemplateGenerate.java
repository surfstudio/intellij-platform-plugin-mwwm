package org.jetbrains.plugins.template.service;

import org.jetbrains.plugins.template.common.CommonString;
import org.jetbrains.plugins.template.common.FileNaming;
import org.jetbrains.plugins.template.common.SpecialFileNamingEnum;
import org.jetbrains.plugins.template.common.path.RecourseImport;
import org.jetbrains.plugins.template.common.path.RecourseScreen;
import org.jetbrains.plugins.template.common.path.RecourseWidget;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TemplateGenerate {
    /// Путь до проекта
    String pathDirectory;

    public void run(String path, String pathDirectory, String nameClass, boolean isScreen)
            throws IOException {
        this.pathDirectory = pathDirectory;
        if (nameClass.isEmpty()) return;

        if (isScreen) {
            generateScreen(path, nameClass);
        } else {
            generateWidget(path, nameClass);
        }
    }

    /// Сгенерировать файлы для Screen
    private void generateScreen(String path, String nameClass) throws IOException {
        final String templateScreenText = getTextFile(RecourseScreen.screen);
        final String templateWmText     = getTextFile(RecourseScreen.wm);
        final String templateRouteText  = getTextFile(RecourseScreen.route);
        final String templateDiText     = getTextFile(RecourseScreen.di);


        final Map<SpecialFileNamingEnum, String> hashMapNameFile = new HashMap<>();

        hashMapNameFile.put(SpecialFileNamingEnum.screen,
                            getPathName(nameClass, SpecialFileNamingEnum.screen));
        hashMapNameFile.put(SpecialFileNamingEnum.wm,
                            getPathName(nameClass, SpecialFileNamingEnum.wm));
        hashMapNameFile.put(SpecialFileNamingEnum.route,
                            getPathName(nameClass, SpecialFileNamingEnum.route));
        hashMapNameFile.put(SpecialFileNamingEnum.di,
                            getPathName(nameClass, SpecialFileNamingEnum.di));


        final String screenText = createFileDart(templateScreenText, nameClass, hashMapNameFile,
                                                 path);
        final String wmText = createFileDart(templateWmText, nameClass, hashMapNameFile, path);
        final String routeText = createFileDart(templateRouteText, nameClass, hashMapNameFile,
                                                path);
        final String diText = createFileDart(templateDiText, nameClass, hashMapNameFile, path);

        writeFile(path, hashMapNameFile.get(SpecialFileNamingEnum.screen),
                  SpecialFileNamingEnum.screen, screenText);
        writeFile(path, hashMapNameFile.get(SpecialFileNamingEnum.wm), SpecialFileNamingEnum.wm,
                  wmText);
        writeFile(path, hashMapNameFile.get(SpecialFileNamingEnum.route),
                  SpecialFileNamingEnum.route, routeText);
        writeFile(path, hashMapNameFile.get(SpecialFileNamingEnum.di), SpecialFileNamingEnum.di,
                  diText);

    }

    /// Сгенерировать файлы для Widget
    private void generateWidget(String path, String nameClass) throws IOException {
        final String widgetText = getTextFile(RecourseWidget.widget);
        final String wmText     = getTextFile(RecourseWidget.wm);
        final String diText     = getTextFile(RecourseWidget.di);

        //        createFileDart(path, widgetText, nameClass, SpecialFileNamingEnum.widget);
        //        createFileDart(path, wmText, nameClass, SpecialFileNamingEnum.wm);
        //        createFileDart(path, diText, nameClass, SpecialFileNamingEnum.di);
    }

    /// Получаем содержимое файла в виде строки
    private String getTextFile(String path) throws IOException {
        final StringBuilder stringBuffer = new StringBuilder();

        final InputStream inputStream = this.getClass()
                                            .getClassLoader()
                                            .getResourceAsStream(path);

        if (inputStream != null) {
            int i;
            while ((i = inputStream.read()) != -1) {
                char ch = (char) i;
                stringBuffer.append(ch);
            }
        }
        return stringBuffer.toString();
    }

    /// создаём текст файла дарт
    private String createFileDart(String textFile, String nameClass,
                                  Map<SpecialFileNamingEnum, String> hashMapPath, String path) {

        final String pathSeparator = path.replace(pathDirectory, "") + File.separator;


        final String importDi = RecourseImport.getImport(
                pathSeparator + CommonString.di + File.separator +
                hashMapPath.get(SpecialFileNamingEnum.di));

        final String importScreen = RecourseImport.getImport(
                pathSeparator + hashMapPath.get(SpecialFileNamingEnum.screen));

        final String importWm = RecourseImport.getImport(
                pathSeparator + hashMapPath.get(SpecialFileNamingEnum.wm));

        String text = textFile.replaceAll(CommonString.regexString, nameClass);
        text = text.replaceAll(CommonString.regexImportDi, importDi);
        text = text.replaceAll(CommonString.regexImportScreen, importScreen);
        text = text.replaceAll(CommonString.regexImportWM, importWm);

        text = text.replaceAll(CommonString.regexImportDi, "");
        text = text.replaceAll(CommonString.regexImportScreen, "");
        text = text.replaceAll(CommonString.regexImportWM, "");
        return text;
    }

    /// записываем в файл текст
    private void writeFile(String path, String nameFile, SpecialFileNamingEnum namingEnum,
                           String textFile) {
        System.out.println(path);

        try {
            File file;
            if (namingEnum == SpecialFileNamingEnum.di) {
                file = new File(
                        path + File.separator + CommonString.di + File.separator + nameFile);
            } else {
                file = new File(path + File.separator + nameFile);
            }


            new File(file.getParent()).mkdirs();//создаём директории, если отсутствуют

            final FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(textFile);
            fileWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /// Имя файла
    private String getPathName(String nameClass, SpecialFileNamingEnum naming) {
        nameClass = getNameFile(nameClass);

        switch (naming) {
            case screen:
                return nameClass + FileNaming.screen;
            case widget:
                return nameClass + FileNaming.widget;
            case wm:
                return nameClass + FileNaming.wm;
            case route:
                return nameClass + FileNaming.route;
            case di:
                return nameClass + FileNaming.di;
            default:
                return "";
        }
    }

    /// создаем имя файла дарт
    private String getNameFile(String nameFIle) {
        final StringBuilder answer    = new StringBuilder();
        final char[]        findUpper = nameFIle.toCharArray();
        for (int i = 0; findUpper.length > i; i++) {
            // пропускаем 1-й символ
            // ascii value in between 65 and 91 is A to Z
            if ((i != 0) && (findUpper[i] >= 65 && findUpper[i] <= 91)) {
                answer.append("_"); //adding only uppercase
            }
            answer.append(findUpper[i]);
        }
        return answer.toString()
                     .toLowerCase(Locale.ROOT);
    }
}
