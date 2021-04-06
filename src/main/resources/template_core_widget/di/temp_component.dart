import 'package:flutter/material.dart';
import 'package:mwwm/mwwm.dart';

class $Temp$Component implements Component {
  $Temp$Component(BuildContext context) {
    _messageController = MaterialMessageController.from(context);
    _dialogController = DefaultDialogController.from(context);

    _wmDependencies = WidgetModelDependencies(
      errorHandler: StandardErrorHandler(
        _messageController,
        _dialogController,
      ),
    );
  }

  MaterialMessageController _messageController;
  DefaultDialogController _dialogController;
  WidgetModelDependencies _wmDependencies;
}

$Temp$Wm create$Temp$Wm(BuildContext context) {
  final component = $Temp$Component(context);

  return $Temp$Wm(
    component._wmDependencies,
  );
}