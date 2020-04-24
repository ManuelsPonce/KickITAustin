package com.example.kickitaustin

class DataSource {
        companion object {

            fun createDataSet(): ArrayList<GamePost> {
                val list = ArrayList<GamePost>()
                list.add(
                    GamePost(
                        "Game 1",
                        "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                        "https://virtualself.co/assets/images/character/b/depth.jpg",
                        "Sally"
                    )
                )
                list.add(
                    GamePost(
                        "Game 2",
                        "The REST API course is complete. You can find the videos here: https://codingwithmitch.com/courses/build-a-rest-api/.",
                        "https://virtualself.co/assets/images/character/b/depth.jpg",
                        "Man"
                    )
                )

                list.add(
                    GamePost(
                        "Game 3",
                        "Justin has been producing online courses for YouTube, Udemy, and his website CodingForEntrepreneurs.com for over 5 years.",
                        "https://virtualself.co/assets/images/character/b/depth.jpg",
                        "John"
                    )
                )
                list.add(
                    GamePost(
                        "Game4",
                        "Vasiliy has been a freelance android developer for several years. He also has some of the best android development courses I've had the pleasure of taking on Udemy.com.",
                        "https://virtualself.co/assets/images/character/b/depth.jpg",
                        "Steven"
                    )
                )
                list.add(
                    GamePost(
                        "Game 5",
                        "Freelancing as an Android developer with Donn Felker.\\r\\n\\r\\nDonn is also:\\r\\n\\r\\n1) Founder of caster.io\\r\\n\\r\\n2) Co-host of the fragmented podcast (fragmentedpodcast.com).",
                        "https://virtualself.co/assets/images/character/b/depth.jpg",
                        "Richelle"
                    )
                )
                list.add(
                    GamePost(
                        "Game 6",
                        "What kind of hobbies do software developers have? It sounds like many software developers don't have a lot of hobbies and choose to focus on work. Is that a good idea?",
                        "https://virtualself.co/assets/images/character/b/depth.jpg",
                        "Jessica"
                    )
                )
                list.add(
                    GamePost(
                        "Game 7",
                        "In this podcast I interviewed the Fullsnack Developer (AKA Nicholas Olsen).\\r\\n\\r\\nNicholas is many things. What I mean by that is, he's good at many things.\\r\\n\\r\\n1. He’s an entrepreneur\\r\\n\\r\\n2. Web developer\\r\\n\\r\\n3. Artist\\r\\n\\r\\n4. Graphic designer\\r\\n\\r\\n5. Musician (drums)\\r\\n\\r\\n6. Professional BodyBuilder.",
                        "https://virtualself.co/assets/images/character/b/depth.jpg",
                        "Guy"
                    )
                )
                list.add(
                    GamePost(
                        "Game 8",
                        "Interviewing a web developer, javascript expert, entrepreneur, freelancer, podcaster, and much more.",
                        "https://virtualself.co/assets/images/character/b/depth.jpg",
                        "Ruby"
                    )
                )
                list.add(
                    GamePost
                        (
                        "Game 9",
                        "Kaushik Gopal is a Senior Android Engineer working in Silicon Valley.\r\n\r\nHe works as a Senior Staff engineer at Instacart.\r\n\r\nInstacart: https://www.instacart.com/",
                        "https://virtualself.co/assets/images/character/b/depth.jpg",
                        "mitch"
                    )
                )
                return list
            }
        }
    }