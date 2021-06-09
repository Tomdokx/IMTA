package com.tomdok.emojiclicker.Abilities

import com.tomdok.emojiclicker.classes.Emote
import com.tomdok.emojiclicker.classes.Hero
import com.tomdok.emojiclicker.classes.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class AbilityDoubleDmgHero(_duration: Long): Ability {

    var duration = _duration

    override fun doEffects(player: Player, hero: Hero, emote: Emote, refreshHPBar: () -> Unit) {

        hero.baseDPS *= 2
        runBlocking {

            launch {

                delay(duration)
            }
        }

        hero.baseDPS /= 2

    }

}