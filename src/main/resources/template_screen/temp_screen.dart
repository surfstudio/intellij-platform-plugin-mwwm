import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:mwwm/mwwm.dart';
$import_di$
$import_wm$

/// Screen
class $Temp$Screen extends CoreMwwmWidget {
  $Temp$Screen([
    WidgetModelBuilder widgetModelBuilder = create$Temp$WidgetModel,
  ]) : super(
          widgetModelBuilder: widgetModelBuilder,
        );

  @override
  State<StatefulWidget> createState() {
    return _$Temp$ScreenState();
  }
}

class _$Temp$ScreenState extends WidgetState<$Temp$WidgetModel> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Text("temp screen"),
    );
  }
}
