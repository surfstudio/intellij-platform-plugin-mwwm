import 'package:flutter/material.dart';
import 'package:mwwm/mwwm.dart';
import 'package:surf_mwwm/surf_mwwm.dart';

/// Screen [$Template$Screen]
class $Template$Screen extends MwwmWidget<$Template$Component> {
  $Template$Screen({
    Key key,
  }) : super(
    key: key,
    widgetModelBuilder: create$Template$WidgetModel,
    dependenciesBuilder: (context) => $Template$Component(context),
    widgetStateBuilder: () => _$Template$ScreenState(),
  );
}

class _$Template$ScreenState extends WidgetState<$Template$WidgetModel> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: Injector.of<$Template$Component>(context).component.scaffoldKey,
      body: Center(
        child: Text("Template screen"),
      ),
    );
  }
}