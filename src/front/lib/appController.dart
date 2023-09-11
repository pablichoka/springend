import 'package:flutter/material.dart';
import 'package:front/login.dart';

final ThemeData _kCalControlTheme = _buildKCalControlTheme();

ThemeData _buildKCalControlTheme(){
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

TextTheme _buildKCalControlTextTheme(TextTheme base){
  return base.copyWith(
    headlineSmall: base.headlineSmall?.copyWith(
      fontFamily: 'ProductSans',
    ),
    headlineMedium: base.headlineMedium?.copyWith(
      fontFamily: 'ProductSans',
    ),
    headlineLarge: base.headlineLarge?.copyWith(
      fontFamily: 'ProductSans',
    ),
  ).apply(
   displayColor: Colors.brown,
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
        '/login': (BuildContext context) => const Login(),
        // '/': (BuildContext context) => const
      },
      theme: _kCalControlTheme,
      debugShowCheckedModeBanner: false,
    );
  }


}