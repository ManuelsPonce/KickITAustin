package com.example.kickitaustin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.android.synthetic.main.activity_create_game.*
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.RowId
import java.sql.Timestamp
import java.util.*

class CreateGameActivity : AppCompatActivity() {

    private lateinit var HOUR: String
    private lateinit var MINUTE: String
    private lateinit var STARTTIME: String

    private lateinit var DAY: String
    private lateinit var MONTH: String
    private lateinit var YEAR: String
    private lateinit var DATEOFGAME: String

    private lateinit var LOPOPTION: String
    private lateinit var LOCATION: String
    private var currentUser: FirebaseUser? = null
    private lateinit var  gameAdapter: GameRecyclerAdapter

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var gamePost = MutableLiveData<List<GamePost>>()
    //private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        currentUser = FirebaseAuth.getInstance().currentUser
        //Log.d("XXX", "NAME OF USER: " + currentUser!!.displayName)

        //Sets up all spinners for creating game page.
        prepareOptionsForCreate()

        button_create.setOnClickListener {
            val newGamePost = GamePost().apply {
                val cUser = currentUser
                if (cUser == null) {
                    gameOwner = "unknown"
                    ownerUid = "unknown"
                    //Log.d("XXX", "XXX, currentUser null!")
                } else {
                    gameOwner = cUser.displayName
                    Log.d("XXX", "NAME OF cUser: " + cUser.displayName)
                    ownerUid = cUser.uid
                    //Log.d("XXX", "ID OF USER: " + cUser.uid)
                }
                location = LOCATION
                levelOflay = LOPOPTION
                dateOfGame = DATEOFGAME
                //Log.d("XXX", "Location of new game: " + location)
               profilePic = null//pictureUUID
                startTime = STARTTIME
                //Log.d("XXX", "StartTime: "+ startTime)
                numberOfPlayers = 1

            }

            //Log.d("XXX", "Right before should save game to database...")
            //Log.d("XXX", "New GAME: " + newGamePost.toString())
            saveGamePostt(newGamePost)
            finish()
        }
        buttonCancel.setOnClickListener {
            finish()
        }
    }

    private fun prepareOptionsForCreate() {
        val levelOfPlayOption = resources.getStringArray(R.array.levelOfPlayOptions)
        if(lopSpinner != null) {
            val lopAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, levelOfPlayOption)
            lopSpinner.adapter = lopAdapter
            //Log.d("XXX", "IN LOP")
            LOPOPTION = ""

            lopSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                            LOPOPTION = levelOfPlayOption[position]
                            //Log.d("XXX", "LEVELOFPLAY: " + LOPOPTION)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val locationOption = resources.getStringArray(R.array.locationOptions)
        if(locationSpinner != null) {
            val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationOption)
            locationSpinner.adapter = locationAdapter
            //Log.d("XXX", "IN Loc")
            LOCATION = ""

            locationSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    LOCATION = locationOption[position]
                    //Log.d("XXX", "LOCATION: " + LOCATION)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        HOUR = ""
        MINUTE = ""
        STARTTIME = ""

        // Create a new instance of TimePickerDialog and return it
        timePicker.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                //set time
                HOUR = hourOfDay.toString()
                if(minute.toString().length > 1)
                    MINUTE = minute.toString()
                else
                    MINUTE = "0"+minute.toString()
                //Log.d("XXX", HOUR+":"+MINUTE)
                STARTTIME = HOUR+":"+MINUTE
            }, hour, minute, false)
            timePickerDialog.show()
        }



        DAY = ""
        MONTH = ""
        YEAR = ""
        DATEOFGAME = ""

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of TimePickerDialog and return it
        datePicker.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                //set time
                DAY = dayOfMonth.toString()
                MONTH = month.toString()
                YEAR = year.toString()
                DATEOFGAME = ""+month+"/"+dayOfMonth+"/"+year
                //Log.d("XXX", DATEOFGAME)
            }, year, month, day)
            datePickerDialog.show()
        }
    }

    fun saveGamePostt(gamePost: GamePost) {
        // XXX Write me.
        // https://firebase.google.com/docs/firestore/manage-data/add-data#add_a_document
        // Remember to set the rowID of the chatRow before saving it
        gamePost.rowId = db.collection("gamePost").document().id

        db.collection("gamePost")
            .document(gamePost.rowId)
            .set(gamePost)
            .addOnSuccessListener {
                getGamePost()
            }
    }


    fun getGamePost() {
        if(FirebaseAuth.getInstance().currentUser == null) {
            Log.d(javaClass.simpleName, "Can't get games, no one is logged in")
            gamePost.value = listOf()
            return
        }
        // XXX Write me.  Limit total number of chat rows to 100
        db.collection("gamePost")
            .limit(100)
            //.orderBy("startTime")
            .addSnapshotListener { querySnapshot, ex ->
                if (ex != null) {
                    Log.w(MainActivity.TAG, "listen:error", ex)
                    return@addSnapshotListener
                }
                Log.d(MainActivity.TAG, "fetch in CreateGameActivity ${querySnapshot!!.documents.size}")
                gamePost.value = querySnapshot.documents.mapNotNull {
                    it.toObject(GamePost::class.java)
                }
                Log.d("XXX",  "GamePosts fetched from gamePost.value: " + gamePost.value.toString())
                gamePost.postValue(gamePost.value)
            }
    }

    fun deleteChatRow(gamePost: GamePost){
        // Delete picture (if any) on the server, asynchronously
        val uuid = gamePost.profilePic
        if(uuid != null) {
            //Storage.deleteImage(uuid)
        }
        Log.d(javaClass.simpleName, "remote chatRow id: ${gamePost.rowId}")

        // XXX delete chatRow
        val gampostId = gamePost.rowId
        db.collection("gamePost").document(gampostId).delete()
            .addOnSuccessListener {
                getGamePost()
            }
    }
}
