import 'package:flutter/material.dart';
import 'package:front/index.dart';

final ThemeData _kCalControlTheme = _buildKCalControlTheme();

ThemeData _buildKCalControlTheme() {
  final ThemeData lightTheme = ThemeData.light();
  return lightTheme.copyWith(
    colorScheme: lightTheme.colorScheme.copyWith(
      primary: Colors.blueAccent,
      onPrimary: Colors.deepPurpleAccent,
      secondary: Colors.tealAccent,
      onSecondary: Colors.white,
    ),
    textTheme: _buildKCalControlTextTheme(lightTheme.textTheme),
  );
}

TextTheme _buildKCalControlTextTheme(TextTheme base) {
  return base.copyWith(
    headlineSmall: base.headlineSmall?.copyWith(
        fontFamily: 'ProductSans',
        fontWeight: FontWeight.w300,
    ),
    headlineMedium: base.headlineMedium?.copyWith(
        fontFamily: 'ProductSans',
        fontWeight: FontWeight.w400
    ),
    headlineLarge: base.headlineLarge?.copyWith(
        fontFamily: 'ProductSans',
        fontWeight: FontWeight.w500
    ),
  ).apply(
    displayColor: Colors.white,
  );
}

class KCalFront extends StatelessWidget {
  const KCalFront({Key? key}) :super (key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'kCal Control',
      initialRoute: "/login",
      routes: {
        '/login': (BuildContext context) => const Index(),
        // '/': (BuildContext context) => const
      },
      theme: _kCalControlTheme,
      debugShowCheckedModeBanner: false,
    );
  }


}