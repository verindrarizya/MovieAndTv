package com.example.movieandtv.utils

import com.example.movieandtv.data.source.local.entity.MovieEntity
import com.example.movieandtv.data.source.local.entity.TvShowEntity
import com.example.movieandtv.data.source.remote.api.TMDBAssets.BASE_IMAGE_URL

object DataDummy {

    fun generateDummyMoviesData(): List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(MovieEntity(
                19404,
                "Dilwale Dulhania Le Jayenge",
                "$BASE_IMAGE_URL/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                "1995-10-20",
                8.7,
                "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
                isFavorite = false
        ))

        movies.add(MovieEntity(
                724089,
                "Gabriel's Inferno Part II",
                "$BASE_IMAGE_URL/x5o8cLZfEXMoZczTYWLrUo1P7UJ.jpg",
                "2020-07-31",
                8.7,
                "Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?",
                isFavorite = false
        ))

        movies.add(MovieEntity(
                278,
                "The Shawshank Redemption",
                "$BASE_IMAGE_URL/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                "1994-09-23",
                8.7,
                "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
                isFavorite = false
        ))

        movies.add(MovieEntity(
                238,
                "The Godfather",
                "$BASE_IMAGE_URL/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                "1972-03-14",
                8.7,
                "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                isFavorite = false
        ))

        movies.add(MovieEntity(
                761053,
                "Gabriel's Inferno Part III",
                "$BASE_IMAGE_URL/fYtHxTxlhzD4QWfEbrC1rypysSD.jpg",
                "2020-11-19",
                8.7,
                "The final part of the film adaption of the erotic romance novel Gabriel's Inferno written by an anonymous Canadian author under the pen name Sylvain Reynard.",
                isFavorite = false
        ))

        return movies
    }

    fun generateDummyTvShowsData(): List<TvShowEntity> {

        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(TvShowEntity(
                100,
                "I Am Not an Animal",
                "$BASE_IMAGE_URL/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
                "2004-05-10",
                9.4,
                "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
                isFavorite = false
        ))

        tvShows.add(TvShowEntity(
                83097,
                "The Promised Neverland",
                "$BASE_IMAGE_URL/oBgRCpAbtMpk1v8wfdsIph7lPQE.jpg",
                "2019-01-11",
                9.1,
                "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \"Mama,\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.\n\nHowever, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama.",
                isFavorite = false
        ))

        tvShows.add(TvShowEntity(
                83095,
                "The Rising of the Shield Hero",
                "$BASE_IMAGE_URL/6cXf5EDwVhsRv8GlBzUTVnWuk8Z.jpg",
                "2019-01-09",
                9.1,
                "Iwatani Naofumi was summoned into a parallel world along with 3 other people to become the world's Heroes. Each of the heroes respectively equipped with their own legendary equipment when summoned, Naofumi received the Legendary Shield as his weapon. Due to Naofumi's lack of charisma and experience he's labeled as the weakest, only to end up betrayed, falsely accused, and robbed by on the third day of adventure. Shunned by everyone from the king to peasants, Naofumi's thoughts were filled with nothing but vengeance and hatred. Thus, his destiny in a parallel World begins...",
                isFavorite = false
        ))

        tvShows.add(TvShowEntity(
                88040,
                "Given",
                "$BASE_IMAGE_URL/pdDCcAq8RNSZNk81PXYoHNUPHjn.jpg",
                "2019-07-12",
                9.1,
                "Tightly clutching his Gibson guitar, Mafuyu Satou steps out of his dark apartment to begin another day of his high school life. While taking a nap in a quiet spot on the gymnasium staircase, he has a chance encounter with fellow student Ritsuka Uenoyama, who berates him for letting his guitar's strings rust and break. Noticing Uenoyama's knowledge of the instrument, Satou pleads for him to fix it and to teach him how to play. Uenoyama eventually agrees and invites him to sit in on a jam session with his two band mates: bassist Haruki Nakayama and drummer Akihiko Kaji.",
                isFavorite = false
        ))

        tvShows.add(TvShowEntity(
                61663,
                "Your Lie in April",
                "$BASE_IMAGE_URL/IGbeFv5Ji4W0x530JcSHiQpamY.jpg",
                "2014-10-10",
                9.0,
                "Kousei Arima was a genius pianist until his mother's sudden death took away his ability to play. Each day was dull for Kousei. But, then he meets a violinist named Kaori Miyazono who has an eccentric playing style. Can the heartfelt sounds of the girl's violin lead the boy to play the piano again?",
                isFavorite = false
        ))

        return tvShows

    }
}