import 'package:flutter/material.dart';
import 'package:surf_injector/surf_injector.dart';
import 'package:surf_mwwm/surf_mwwm.dart';

/// di for [$Temp$WidgetModel]
class $Template$Component implements WidgetComponent {
  $Template$Component(BuildContext context) : super(context) {
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

/// builder by $Template$WidgetModel
$Template$WidgetModel create$Template$WidgetModel(BuildContext context) {
  final component = Injector.of<$Template$Component>(context).component;
  return $Template$WidgetModel(
    component._wmDependencies,
    component._navigator,
  );
}