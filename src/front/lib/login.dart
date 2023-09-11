import 'package:flutter/material.dart';

class Login extends StatefulWidget {
  final String title = "kCal Control";

  const Login({super.key});

  @override
  State<Login> createState() => _LoginState();
}

class _LoginState extends State<Login> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        extendBodyBehindAppBar: true,
        appBar: AppBar(
          leading: const Icon(Icons.food_bank),
          foregroundColor: Colors.brown,
          elevation: 0,
          title: Text(
            widget.title,
            style: Theme.of(context).textTheme.headlineLarge,
          ),
          backgroundColor: const Color.fromRGBO(255, 255, 255, 0.75),
        ),
        body: Container(
          decoration: const BoxDecoration(
            image: DecorationImage(
                fit: BoxFit.cover, image: AssetImage('images/index_back.jpg')),
          ),
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                const SizedBox(
                  height: 20,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Card(
                      borderOnForeground: true,
                      color: const Color.fromRGBO(255, 255, 255, 0.75),
                      child: TextButton(
                          onPressed: () {},
                          child: Text(
                            'Sign up',
                            style: Theme.of(context).textTheme.headlineSmall,
                          )),
                    ),
                    Card(
                      child: TextButton(
                          onPressed: () {},
                          child: Text(
                            'Log in',
                            style: Theme.of(context).textTheme.headlineSmall,
                          )),
                    )
                  ],
                )
              ],
            ),
          ),
        ));
  }
}
