package com.tomdok.emojiclicker.Abilities

import com.tomdok.emojiclicker.classes.Emote
import com.tomdok.emojiclicker.classes.Hero
import com.tomdok.emojiclicker.classes.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AbilityDoExtraDamage(_damage: Double, _duration: Long): Ability {

    var damage = _damage
    var duration = _duration

    override fun doEffects(player: Player, hero: Hero, emote: Emote, refreshHPBar: () -> Unit) {

       runBlocking {

           launch {

               for(i in 0..4) {

                   emote.getHurt(damage)
                   refreshHPBar.invoke()

                   delay(duration / 4)
               }
           }
       }
    }
}