import 'package:flutter/material.dart';
import 'package:mwwm/mwwm.dart';
import 'package:surf_mwwm/surf_mwwm.dart';

/// Screen [$Temp$Screen]
class $Temp$Screen extends MwwmWidget<$Temp$Component> {
  $Temp$Screen({
    Key key,
  }) : super(
    key: key,
    widgetModelBuilder: create$Temp$Wm,
    dependenciesBuilder: (context) => $Temp$Component(context),
    widgetStateBuilder: () => _$Temp$ScreenState(),
  );
}

class _$Temp$ScreenState extends WidgetState<$Temp$Wm> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: Injector
          .of<AppComponent>(context)
          .component
          .scaffoldKey,
      body: Center(
        child: Text("Template screen"),
      ),
    );
  }
}