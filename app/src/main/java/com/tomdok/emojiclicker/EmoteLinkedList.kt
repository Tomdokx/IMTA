package com.tomdok.emojiclicker

import com.tomdok.emojiclicker.classes.Emote

class EmoteLinkedList(){
    private var actual: Item? = null
    private var first: Item? = null

    private class Item(emote: Emote, next: Item?){
        var emote = emote
        var next = next
    }

    fun add(emote: Emote){
        val item = Item(emote, null)
        if(first == null){
            first = item
            actual = first
        }
        else{
            actual?.next = item
            actual = actual?.next
        }
    }

    fun addAll(emotes: List<Emote>){
        emotes.forEach {
            add(it)
        }
    }

    fun rewind(){
        actual = first
    }

    fun hasNext(): Boolean {
        return actual?.next != null
    }

    fun next(){
        if (hasNext()){
            actual = actual?.next
        }
    }

    fun actual(): Emote {
        return actual!!.emote
    }

}