package com.example.task_2

class ModelClass {

    companion object {
        const val USER_INFO_LAYOUT = 0
        const val AD_LAYOUT = 1
        const val IMAGE_LAYOUT = 2
    }

    fun getViewType(): Any {
        return viewType
    }

    var viewType: Int

    //userinfolayout
    var imageResource = 0
        private set
    var title: String? = null
        private set
    var body: String? = null
        private set

    constructor(viewType: Int, imageResource: Int, title: String?, body: String?) {
        this.imageResource = imageResource
        this.title = title
        this.body = body
        this.viewType = viewType
    }

    //ad layout
    var adText: String? = null

    constructor(viewType: Int, adText: String?) {
        this.viewType = viewType
        this.adText = adText
    }

    //image layout
    var resource = 0
        private set

    constructor(viewType: Int, resource: Int) {
        this.viewType = viewType
        this.resource = resource
    }

    constructor(viewType: Int) {
        this.viewType = viewType
    }


}