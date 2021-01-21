import 'package:flutter/material.dart';
import 'package:surf_injector/surf_injector.dart';
import 'package:surf_mwwm/surf_mwwm.dart';


/// di for [$Temp$Wm]
class $Temp$Component extends WidgetComponent {
  $Temp$Component(BuildContext context) : super(context) {
    final appComponent = Injector
        .of<AppComponent>(context)
        .component;
    _navigator = Navigator.of(context);
    _messageController = MaterialMessageController(scaffoldKey);
    _dialogController = DefaultDialogController(scaffoldKey);

    _wmDependencies = WidgetModelDependencies(
      errorHandler: StandardErrorHandler(
          _messageController,
          _dialogController
      ),
    );
  }


  NavigatorState _navigator;
  MessageController _messageController;
  DialogController _dialogController;
  WidgetModelDependencies _wmDependencies;
}

/// builder by TemplateWm
$Temp$Wm create$Temp$Wm(BuildContext context) {
  final component = Injector
      .of<$Temp$Component>(context)
      .component;
  return $Temp$Wm(
    component._wmDependencies,
    component._navigator,
  );
}
