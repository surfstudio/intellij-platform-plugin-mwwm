import 'package:flutter/material.dart';
import 'package:surf_injector/surf_injector.dart';
import 'package:surf_mwwm/surf_mwwm.dart';

/// di for [$Temp$WidgetModel]
class $Temp$Component implements WidgetComponent {
  $Temp$Component(BuildContext context) : super(context) {
    final parent = Injector.of<AppComponent>(context).component;
    _navigator = Navigator.of(context);
    _messageController = MaterialMessageController.from(context);
    _dialogController = DefaultDialogController.from(context);

    _wmDependencies = WidgetModelDependencies(
      errorHandler: StandardErrorHandler(
          _messageController,
          _dialogController,
          parent.scInteractor,
      ),
    );
  }

  NavigatorState _navigator;
  MessageController _messageController;
  DialogController _dialogController;
  WidgetModelDependencies _wmDependencies;
}

/// builder by $Temp$WidgetModel
$Temp$WidgetModel create$Temp$WidgetModel(BuildContext context) {
  final component = Injector.of<$Temp$Component>(context).component;
  return $Temp$WidgetModel(
    component._wmDependencies,
    component._navigator,
  );
}