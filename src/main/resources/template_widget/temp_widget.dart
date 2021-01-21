import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:mwwm/mwwm.dart';
import 'di/temp_wm_builder.dart';
import 'temp_wm.dart';

/// Widget
class $Temp$Widget extends CoreMwwmWidget {
  $Temp$Widget([
    WidgetModelBuilder widgetModelBuilder = create$Temp$WidgetModel,
  ]) : super(
    widgetModelBuilder: widgetModelBuilder,
  );

  @override
  State<StatefulWidget> createState() {
    return _$Temp$WidgetState();
  }
}

class _$Temp$WidgetState extends WidgetState<$Temp$WidgetModel> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Text("temp screen"),
    );
  }
}
