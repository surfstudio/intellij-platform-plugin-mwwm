import 'package:flutter/material.dart';
import 'package:mwwm/mwwm.dart';
import 'package:surf_mwwm/surf_mwwm.dart';

/// Screen [$Temp$Screen]
class $Temp$Screen extends MwwmWidget<$Temp$Component> {
  $Temp$Screen({
    Key key,
  }) : super(
    key: key,
    widgetModelBuilder: create$Temp$WidgetModel,
    dependenciesBuilder: (context) => $Temp$Component(context),
    widgetStateBuilder: () => _$Temp$ScreenState(),
  );
}

class _$Temp$ScreenState extends WidgetState<$Temp$WidgetModel> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: Injector.of<$Temp$Component>(context).component.scaffoldKey,
      body: Center(
        child: Text("Template screen"),
      ),
    );
  }
}