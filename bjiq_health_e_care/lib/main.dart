import 'dart:ui';
import 'package:flutter/material.dart';

void main() {
  runApp(
    MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.green[100],
        appBar: AppBar(
          backgroundColor: Color(1713185),
          title: Text('Health-e Care || Glossary of Medicines'),
        ),
        body: Center(
          child: Container(
            padding: EdgeInsets.only(left: 15.0, top: 20.0),
            alignment: Alignment.center,
            color: Colors.white70,
            child: Column(
              children: <Widget>[
                Row(
                  children: <Widget>[
                    Expanded(
                      child: Text("Medicines",
                          textDirection: TextDirection.ltr,
                        style: TextStyle(
                          decoration:TextDecoration.none,
                          fontSize:30.0,
                          fontFamily: 'User',
                          fontWeight: FontWeight.w700,
                          color: Colors.black54
                        )
                      ),
                    ),
                    Expanded(
                      child: Text("Description and uses",
                          textDirection: TextDirection.ltr,
                          style: TextStyle(
                              decoration:TextDecoration.none,
                              fontSize:30.0,
                              fontFamily: 'User',
                              fontWeight: FontWeight.w700,
                              color: Colors.black54
                          )
                      ),
                    )
                  ]
                ),
                Column(
                  children: [
                    SizedBox(
                      height: 25,
                    )
                  ],
                ),
///////////////////////////////////////////////////////////////////////////////////////////////////////
                Row(
                    children: <Widget>[
                      Expanded(
                        child: Text("Acetylcysteine",
                            textDirection: TextDirection.ltr,
                            style: TextStyle(
                                decoration:TextDecoration.none,
                                fontSize:20.0,
                                fontFamily: 'User',
                                fontWeight: FontWeight.w400,
                                color: Colors.black54
                            )
                        ),
                      ),
                      Expanded(
                        child: Text("used to help prevent or lessen liver damage caused by an overdose of acetaminophen.",
                            textDirection: TextDirection.ltr,
                            style: TextStyle(
                                decoration:TextDecoration.none,
                                fontSize:20.0,
                                fontFamily: 'User',
                                fontWeight: FontWeight.w400,
                                color: Colors.black54
                            )
                        ),
                      )
                    ]
                ),
                Column(
                  children: [
                    SizedBox(
                      height: 25,
                    )
                  ],
                ),
//////////////////////////////////////////////////////////////////////////////////////////////////////////
                Row(
                    children: <Widget>[
                      Expanded(
                        child: Text("Butamirate",
                            textDirection: TextDirection.ltr,
                            style: TextStyle(
                                decoration:TextDecoration.none,
                                fontSize:20.0,
                                fontFamily: 'User',
                                fontWeight: FontWeight.w400,
                                color: Colors.black54
                            )
                        ),
                      ),
                      Expanded(
                        child: Text("used for the treatment of dry cough.",
                            textDirection: TextDirection.ltr,
                            style: TextStyle(
                                decoration:TextDecoration.none,
                                fontSize:20.0,
                                fontFamily: 'User',
                                fontWeight: FontWeight.w400,
                                color: Colors.black54
                            )
                        ),
                      )
                    ]
                ),
              ],
            )
          ),
        )
      )
    )
  );
}