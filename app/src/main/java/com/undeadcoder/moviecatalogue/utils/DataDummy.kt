package com.undeadcoder.moviecatalogue.utils

import com.undeadcoder.moviecatalogue.data.source.local.entity.MovieEntity
import com.undeadcoder.moviecatalogue.data.source.local.entity.TvShowEntity
import com.undeadcoder.moviecatalogue.data.source.remote.response.*

object DataDummy {

    fun generateDummyMovies(): List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(
                MovieEntity(
                        1,
                        "Zack Snyder's Justice League",
                        "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                        "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                        "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                        8.5,
                        null,
                        "2021-03-18",
                        242,
                        false
                )
        )

        movies.add(
                MovieEntity(
                        2,
                        "Mortal Kombat",
                        "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                        "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                        "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                        7.6,
                        null,
                        "2021-04-07",
                        110,
                        false
                )
        )

        movies.add(
                MovieEntity(
                        3,
                        "Godzilla vs. Kong",
                        "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                        "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                        "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                        8.1,
                        null,
                        "2021-03-24",
                        113,
                        false
                )
        )
        return movies
    }

    fun generateDummyTvShows(): List<TvShowEntity> {

        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
                TvShowEntity(
                        1,
                        "Money Heist",
                        "To carry out the biggest heist in history, a mysterious man called The Professor recruits a band of eight robbers who have a single characteristic: none of them has anything to lose. Five months of seclusion - memorizing every step, every detail, every probability - culminate in eleven days locked up in the National Coinage and Stamp Factory of Spain, surrounded by police forces and with dozens of hostages in their power, to find out whether their suicide wager will lead to everything or nothing.",
                        "/MoEKaPFHABtA1xKoOteirGaHl1.jpg",
                        "/xGexTKCJDkl12dTW4YCBDXWb1AD.jpg",
                        8.3,
                        null,
                        "2017-05-02",
                        false
                )
        )

        tvShow.add(
                TvShowEntity(
                        2,
                        "The Good Doctor",
                        "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                        "/z1K4mJwISETia59rrnMdXxzoSrZ.jpg",
                        "/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
                        8.6,
                        null,
                        "2017-09-25",
                        false
                )
        )

        tvShow.add(
                TvShowEntity(
                        3,
                        "The Falcon and the Winter Soldier",
                        "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                        "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                        "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                        7.9,
                        null,
                        "2021-03-19",
                        false
                )
        )

        return tvShow
    }

    fun generateDetailMovie(): MovieEntity =
            MovieEntity(
                    527774,
                    "Raya and the Last Dragon",
                    "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                    "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                    "/hJuDvwzS0SPlsE6MNFOpznQltDZ.jpg",
                    8.2,
                    "Animation, Adventure, Fantasy, Family, Action",
                    "2021-03-03",
                    107,
                    false,
            )

    fun generateDetailTvShow(): TvShowEntity =
            TvShowEntity(
                    71712,
                    "The Good Doctor",
                    "During part two of the two-part season finale, Dr. Shaun Murphy must perform a risky surgery on a patient without electricity when the power suddenly goes out. Meanwhile Lim and Dr. Mateo Rendón Osma’s relationship deepens as they overcome difficulties during the surgery.",
                    "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                    "/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
                    8.6,
                    "Drama",
                    "2017-09-25",
                    false,
            )

    fun getDummyRemoteMovies(): List<Movie> {
        return listOf(
                Movie(
                        false,
                        "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                        listOf(35, 80),
                        337404,
                        "en",
                        "Cruella",
                        "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                        3143.226,
                        "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                        "2021-05-26",
                        "Cruella",
                        false,
                        8.6,
                        2788
                ),
                Movie(
                        false,
                        "/qi6Edc1OPcyENecGtz8TF0DUr9e.jpg",
                        listOf(27, 9648, 53),
                        423108,
                        "en",
                        "The Conjuring: The Devil Made Me Do It",
                        "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.",
                        2990.744,
                        "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                        "2021-05-25",
                        "The Conjuring: The Devil Made Me Do It",
                        false,
                        8.2,
                        2143
                ),
                Movie(
                        false,
                        "/70AV2Xx5FQYj20labp0EGdbjI6E.jpg",
                        listOf(80, 28),
                        637649,
                        "en",
                        "Wrath of Man",
                        "A cold and mysterious new security guard for a Los Angeles cash truck company surprises his co-workers when he unleashes precision skills during a heist. The crew is left wondering who he is and where he came from. Soon, the marksman's ultimate motive becomes clear as he takes dramatic and irrevocable steps to settle a score.",
                        2069.753,
                        "/M7SUK85sKjaStg4TKhlAVyGlz3.jpg",
                        "2021-04-22",
                        "Wrath of Man",
                        false,
                        7.8,
                        936
                )
        )
    }

    fun getDummyRemoteTvShows(): List<TvShow> {
        return listOf(
                TvShow(
                        "/Afp8OhiO0Ajb3NPoCBvfu2pqaeO.jpg",
                        "2021-06-09",
                        listOf(18, 10765),
                        84958,
                        "Loki",
                        listOf("US"),
                        "en",
                        "Loki",
                        "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                        6160.834,
                        "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                        8.1,
                        2789
                ),
                TvShow(
                        "/xpba0Dxz3sxV3QgYLR8UIe1LAAX.jpg",
                        "2021-06-04",
                        listOf(18, 10765),
                        103768,
                        "Sweet Tooth",
                        listOf("US"),
                        "en",
                        "Sweet Tooth",
                        "On a perilous adventure across a post-apocalyptic world, a lovable boy who's half-human and half-deer searches for a new beginning with a gruff protector.",
                        999.955,
                        "/rgMfhcrVZjuy5b7Pn0KzCRCEnMX.jpg",
                        8.1,
                        343
                ),
                TvShow(
                        "/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg",
                        "2016-01-25",
                        listOf(80, 10765),
                        63174,
                        "Lucifer",
                        listOf("US"),
                        "en",
                        "Lucifer",
                        "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                        895.311,
                        "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                        8.5,
                        9255
                )
        )
    }

    fun getDummyRemoteDetailMovie(): MovieDetailResponse {
        return MovieDetailResponse(
                false,
                "/hJuDvwzS0SPlsE6MNFOpznQltDZ.jpg",
                100000000,
                listOf(
                        Genre(
                                16,
                                "Animation"
                        ),
                        Genre(
                                12,
                                "Adventure"
                        ),
                        Genre(
                                14,
                                "Fantasy"
                        ),
                        Genre(
                                10751,
                                "Family"
                        ),
                        Genre(
                                28,
                                "Action"
                        ),
                ),
                "https://movies.disney.com/raya-and-the-last-dragon",
                527774,
                "tt5109280",
                "en",
                "Raya and the Last Dragon",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                933.073,
                "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "2021-03-03",
                120448610,
                107,
                listOf(
                        SpokenLanguage(
                                "English",
                                "en",
                                "English"
                        )
                ),
                "Released",
                "A quest to save her world.",
                "Raya and the Last Dragon",
                false,
                8.2,
                3532
        )
    }

    fun getDummyRemoteDetailTvShow(): TVShowDetailResponse {
        return TVShowDetailResponse(
                "/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
                listOf(43),
                "2017-09-25",
                listOf(
                        Genre(
                                id = 18,
                                name = "Drama"
                        )
                ),
                "http://abc.go.com/shows/the-good-doctor",
                71712,
                true,
                listOf("en"),
                "2021-06-07",
                "The Good Doctor",
                listOf(
                        Network(
                                2,
                                "/ndAvF4JLsliGreX87jAc9GdjmJY.png",
                                "ABC",
                                "US"
                        ),
                ),
                76,
                4,
                listOf("US"),
                "en",
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                705.758,
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                listOf(
                        SpokenLanguage(
                                "English",
                                "en",
                                "English"
                        )
                ),
                "Returning Series",
                "His mind is a mystery, his methods are a miracle.",
                "Scripted",
                8.6,
                8646
        )
    }

}