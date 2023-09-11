import 'package:flutter/material.dart';
import 'package:front/signup.dart';

import 'login.dart';

class Index extends StatelessWidget {
  const Index({super.key});

  final String title = "kCal Control";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          toolbarHeight: 80,
          elevation: 0,
          centerTitle: true,
          title: Text(title, style: Theme.of(context).textTheme.headlineLarge),
          backgroundColor: Colors.transparent,
          flexibleSpace: Container(
            decoration: const BoxDecoration(
              gradient: LinearGradient(
                begin: Alignment.topRight,
                end: Alignment.bottomLeft,
                colors: [Colors.blueAccent, Colors.grey, Colors.teal],
              ),
            ),
          ),
        ),
        body: Column(
          mainAxisAlignment: MainAxisAlignment.end,
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Card(
                  borderOnForeground: true,
                  color: const Color.fromRGBO(255, 255, 255, 0.75),
                  child: TextButton(
                      onPressed: () {
                        Navigator.push(context,
                            MaterialPageRoute(builder: (context) => Signup()));
                      },
                      child: Text(
                        'Sign up',
                        style: Theme.of(context).textTheme.headlineSmall,
                      )),
                ),
                Card(
                  child: TextButton(
                      onPressed: () {
                        Navigator.push(context,
                            MaterialPageRoute(builder: (context) => Login()));
                      },
                      child: Text(
                        'Log in',
                        style: Theme.of(context).textTheme.headlineSmall,
                      )),
                )
              ],
            ),
            const SizedBox(
              height: 40,
            )
          ],
        ));
  }
}
