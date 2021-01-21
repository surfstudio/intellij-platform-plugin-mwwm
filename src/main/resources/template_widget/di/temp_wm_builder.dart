/// di for [$Temp$WidgetModel]
$Temp$WidgetModel create$Temp$WidgetModel(
    BuildContext context, {
      GlobalKey<ScaffoldState> scaffoldKey,
    }) {

  scaffoldKey ??= GlobalKey<ScaffoldState>();

  final dependencies = WidgetModelDependencies(
    errorHandler: MwwmErrorHandler(scaffoldKey),
  );

  return $Temp$WidgetModel(
    dependencies,
    Navigator.of(context),
    scaffoldKey,
  );
}
