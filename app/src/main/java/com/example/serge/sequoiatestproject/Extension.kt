package com.example.serge.sequoiatestproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


//Расширим ViewGroup своей функцией inflate, что бы меньше писать ;)
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
