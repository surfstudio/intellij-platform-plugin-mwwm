import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:mwwm/mwwm.dart';
import 'package:surf_mwwm/surf_mwwm.dart';

/// Widget [$Temp$Widget]
class $Temp$Widget extends MwwmWidget<$Temp$Component> {
  $Temp$Widget({
    Key key,
  }) : super(
    key: key,
    widgetModelBuilder: create$Temp$Wm,
    dependenciesBuilder: (context) => $Temp$Component(context),
    widgetStateBuilder: () => _$Temp$WidgetState(),
  );
}

class _$Temp$WidgetState extends WidgetState<$Temp$Wm> {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text("Template"),
    );
  }
}