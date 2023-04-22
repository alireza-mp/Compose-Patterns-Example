package com.alirezamp.compose_patterns_example.data.local.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class PostLocalDto : RealmObject {
    //@PrimaryKey
    //var _id: ObjectId = ObjectId.invoke()
    @PrimaryKey
    var pId: Int = 0
    var userId: Int = 0
    var title: String = ""
    var body: String = ""
}

