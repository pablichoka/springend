import 'dart:convert';
import 'dart:js_interop';

import 'package:flutter/material.dart';
import 'package:front/services/globals.dart';
import 'package:front/signup.dart';
import 'package:http/http.dart' as http;

import 'login.dart';

class Index extends StatefulWidget {
  const Index({super.key});

  @override
  State<Index> createState() => _IndexState();
}

class _IndexState extends State<Index> {
  final String title = "kCal Control";

  late Future<Map<String, dynamic>> message;

  Future<Map<String, dynamic>> _getMessage() async {
    try {
      final url = Uri.https(baseUrl,'/');
      final response = await http.get(url);
      print('codigo de error: ${response.statusCode}');
      if (response.statusCode == 200) {
        String body = utf8.decode(response.bodyBytes);
        final jsonData = jsonDecode(body);
        print(jsonData);
        return jsonData;
      } else {
        throw Exception('Message not received');
      }
    } catch (e) {
      print('Error: $e');
      rethrow;
    }
  }

  @override
  void initState() {
    super.initState();
    message = _getMessage();
  }

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
