# SurfMwwm Gen

<!-- Plugin description -->
Generate template for surf_mwwm and mwwm code.

Плагин может генерировать как из включённого темплейта, так и кастомного.

После установки плагина, в меню появится дополнительное пунк Surf MWWM. При выборе варианты темплейта, вызовится
диалоговое окно, в котом необходимо ввести имя виджета.

Реализовано 3 варианта темплейта - для screen и widget https://pub.dev/packages/surf_mwwm и widget
для https://pub.dev/packages/mwwm.

Для генерации кастомного темплейта, необходимо выполнить следующие условия:

1 - Директория темплейтов должна находится в корне проекта, в директории /mwwm_generator/templates/

2 - Папки для вариантов темплейта находятся в директории templates должны иметь названия surf_mwwm_screen,
surf_mwwm_widget соответственно для screen и widget https://pub.dev/packages/surf_mwwm и mwwm_widget
для https://pub.dev/packages/mwwm.

3 - Имена файлов автоматически, содержащие слово "template" будут заменены на имя введённое в название виджета.

4 - В теле файла все Template будут замены на имя введённое в название виджета.

5 - Плагин имеет возможность генерировать импорты, для это необходимо создать импорт "import 'package:
PathTemplate/folder/template_wm.dart';", где PathTemplate будет заменён на путь до директории темплейта, folder -
необходим, если файлы импорта лежат глубже, чем директория создания темплейта, к примеру для импорта component, folder
нужно заменить di.

<!-- Plugin description end -->