package com.tomdok.emojiclicker

    fun tCoinsToString(tCoins: Int): String {

        if (tCoins/1000 >= 1) {

            if (tCoins > Int.MAX_VALUE){

                return "XXX"
            } else if (tCoins / 1000000000 >= 1) {

                return "%.2f B".format(tCoins / 1000000000.0)
            } else if (tCoins / 1000000 >= 1) {

                return "%.2f M".format(tCoins / 1000000.0)
            } else {

                return "%.2f K".format(tCoins / 1000.0)
            }
        }

            return tCoins.toString()


    }