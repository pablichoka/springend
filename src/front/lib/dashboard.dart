// import 'package:flutter/material.dart';
//
// class Index extends StatefulWidget {
//   const Index({super.key});
//
//   final String title = "kCal Control";
//
//   @override
//   State<StatefulWidget> createState() => _Index();
// }
//
// class _Index extends State<Index> {
//   Widget _currentScreen = const Signup();
//
//   void _changeScreen(Widget screen) {
//     setState(() {
//       _currentScreen = screen;
//     });
//   }
//
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(
//         toolbarHeight: 80,
//         elevation: 0,
//         centerTitle: true,
//         title: Text(widget.title,
//             style: Theme.of(context).textTheme.headlineLarge),
//         backgroundColor: Colors.transparent,
//         flexibleSpace: Container(
//           decoration: const BoxDecoration(
//             gradient: LinearGradient(
//               begin: Alignment.topRight,
//               end: Alignment.bottomLeft,
//               colors: [Colors.blueAccent, Colors.grey, Colors.teal],
//             ),
//           ),
//         ),
//       ),
//       body: _currentScreen,
//       bottomNavigationBar:  BottomNavigationBar(
//         elevation: 10,
//         items: const [
//           BottomNavigationBarItem(
//             icon: Icon(Icons.home),
//             label: 'Pantalla 1',
//           ),
//           BottomNavigationBarItem(
//             icon: Icon(Icons.business),
//             label: 'Pantalla 2',
//           ),
//         ],
//         onTap: (index) {
//           if (index == 0) {
//             _changeScreen(Signup());
//           } else {
//             _changeScreen(Login());
//           }
//         },
//       ),
//     );
//   }
// }
