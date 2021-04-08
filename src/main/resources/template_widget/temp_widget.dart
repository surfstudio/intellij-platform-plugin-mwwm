import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:mwwm/mwwm.dart';
import 'package:surf_mwwm/surf_mwwm.dart';

/// Widget [$Template$Widget]
class $Template$Widget extends MwwmWidget<$Template$Component> {
  $Template$Widget({
    Key key,
  }) : super(
    key: key,
    widgetModelBuilder: create$Template$WidgetModel,
    dependenciesBuilder: (context) => $Template$Component(context),
    widgetStateBuilder: () => _$Template$WidgetState(),
  );
}

class _$Template$WidgetState extends WidgetState<$Template$WidgetModel> {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text("Template"),
    );
  }
}