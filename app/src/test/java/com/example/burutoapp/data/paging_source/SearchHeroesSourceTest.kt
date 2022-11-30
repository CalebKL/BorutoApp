package com.example.burutoapp.data.paging_source

import androidx.paging.PagingSource
import com.example.burutoapp.data.remote.BorutoApi
import com.example.burutoapp.data.remote.FakeBorutoApi
import com.example.burutoapp.domain.models.Hero
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SearchHeroesSourceTest {
    private lateinit var borutoApi: BorutoApi

    private lateinit var heroes:List<Hero>

    @Before
    fun setup(){
       borutoApi = FakeBorutoApi()
       heroes = listOf(
           Hero(
               id = 1,
               name = "Sasuke",
               image = "/images/sasuke.jpg",
               about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
               rating = 5.0,
               power = 98,
               month = "July",
               day = "23rd",
               family = listOf(
                   "Fugaku",
                   "Mikoto",
                   "Itachi",
                   "Sarada",
                   "Sakura"
               ),
               abilities = listOf(
                   "Sharingan",
                   "Rinnegan",
                   "Sussano",
                   "Amateratsu",
                   "Intelligence"
               ),
               natureTypes = listOf(
                   "Lightning",
                   "Fire",
                   "Wind",
                   "Earth",
                   "Water"
               )
           ),
           Hero(
               id = 2,
               name = "Naruto",
               image = "/images/naruto.jpg",
               about = "Naruto Uzumaki (うずまきナルト, Uzumaki Naruto) is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood. After joining Team Kakashi, Naruto worked hard to gain the village's acknowledgement all the while chasing his dream to become Hokage.",
               rating = 5.0,
               power = 98,
               month = "Oct",
               day = "10th",
               family = listOf(
                   "Minato",
                   "Kushina",
                   "Boruto",
                   "Himawari",
                   "Hinata"
               ),
               abilities = listOf(
                   "Rasengan",
                   "Rasen-Shuriken",
                   "Shadow Clone",
                   "Senin Mode"
               ),
               natureTypes = listOf(
                   "Wind",
                   "Earth",
                   "Lava",
                   "Fire"
               )
           ),
           Hero(
               id = 3,
               name = "Sakura",
               image = "/images/sakura.jpg",
               about = "Sakura Uchiha (うちはサクラ, Uchiha Sakura, née Haruno (春野)) is a kunoichi of Konohagakure. When assigned to Team 7, Sakura quickly finds herself ill-prepared for the duties of a shinobi. However, after training under the Sannin Tsunade, she overcomes this, and becomes recognised as one of the greatest medical-nin in the world.",
               rating = 4.5,
               power = 92,
               month = "Mar",
               day = "28th",
               family = listOf(
                   "Kizashi",
                   "Mebuki",
                   "Sarada",
                   "Sasuke"
               ),
               abilities = listOf(
                   "Chakra Control",
                   "Medical Ninjutsu",
                   "Strength",
                   "Intelligence"
               ),
               natureTypes = listOf(
                   "Earth",
                   "Water",
                   "Fire"
               )
           )
       )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `search api with existing hero name, expect single hero result, assert LoadResult_Page`() =
        runBlockingTest {
            val heroesSource = SearchHeroesSource(borutoApi = borutoApi, query = "Sasuke")
            assertEquals<PagingSource.LoadResult<Int,Hero>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(heroes.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroesSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `search api with existing hero name, expect multiple hero result, assert LoadResult_Page`() =
        runBlockingTest {
            val heroesSource = SearchHeroesSource(borutoApi = borutoApi, query = "Sa")
            assertEquals<PagingSource.LoadResult<Int,Hero>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(heroes.first(), heroes[2]),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroesSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }
}