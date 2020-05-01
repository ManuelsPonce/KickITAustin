package com.example.kickitaustin

class DataSource {
        companion object {
            var list2: List<GamePost> ?= null

            fun createDataSet(listGamePost: List<GamePost>) {
                list2 = listGamePost
            }
            val list: MutableList<GamePost> = mutableListOf()
            fun createDataSetTwo(): MutableList<GamePost> {
                list.add(
                    GamePost(
                        "Game 1",
                        "https://virtualself.co/assets/images/character/b/depth.jpg",
                        "Manuel",
                        "12:12",
                        1
                    )
                )
//                list.add(
//                    GamePost(
//                        "Game 2",
//                        "https://virtualself.co/assets/images/character/b/depth.jpg",
//                        "Man",
//                        "12:09",
//                        2
//                    )
//                )
//
//                list.add(
//                    GamePost(
//                        "Game 3",
//                        "https://virtualself.co/assets/images/character/b/depth.jpg",
//                        "John",
//                        "12:12",
//                        3
//                    )
//                )
//                list.add(
//                    GamePost(
//                        "Game4",
//                        "https://virtualself.co/assets/images/character/b/depth.jpg",
//                        "Steven",
//                        "7:12",
//                        4
//                    )
//                )
//                list.add(
//                    GamePost(
//                        "Game 5",
//                        "https://virtualself.co/assets/images/character/b/depth.jpg",
//                        "Richelle",
//                        "9:09",
//                        5
//                    )
//                )
//                list.add(
//                    GamePost(
//                        "Game 6",
//                        "https://virtualself.co/assets/images/character/b/depth.jpg",
//                        "Jessica",
//                        "1:09",
//                        6
//                    )
//                )
//                list.add(
//                    GamePost(
//                        "Game 7",
//                        "https://virtualself.co/assets/images/character/b/depth.jpg",
//                        "Guy",
//                        "2:21",
//                        7
//                    )
//                )
//                list.add(
//                    GamePost(
//                        "Game 8",
//                        "https://virtualself.co/assets/images/character/b/depth.jpg",
//                        "Ruby",
//                        "12:30",
//                        8
//                    )
//                )
//                list.add(
//                    GamePost
//                        (
//                        "Game 9",
//                        "https://virtualself.co/assets/images/character/b/depth.jpg",
//                        "mitch",
//                        "12:10",
//                        9
//                    )
                return list
            }
        }
    }
