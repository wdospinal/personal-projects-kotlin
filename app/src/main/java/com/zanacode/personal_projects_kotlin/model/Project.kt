package com.zanacode.personal_projects_kotlin.model

/**
 * Created by dospinal on 13/07/2017.
 */

class Project {

    var name: String = ""
    var url: String = ""
    var likes: Long = 0
    var content: String = ""

    constructor() {
    }

    constructor(name: String, url: String, likes: Long, content: String) {
        this.name = name
        this.url = url
        this.likes = likes
        this.content = content
    }
}