import 'package:flutter/material.dart';
import 'package:front/services/netService.dart';

import 'appController.dart';

void main() {
  var service = NetworkService();
  service.getData('/').then((value) => print('Valor: ${value.body}'));
  runApp(const KCalFront());
}
