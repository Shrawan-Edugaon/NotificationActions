package com.example.notificationactions

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    lateinit var mActionStylebtn:Button
    val NOTIFICATION = 4
    val channelId = "NOTIFICATION_CHANNEL_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mActionStylebtn = findViewById(R.id.NotificationActionBtn)
        mActionStylebtn.setOnClickListener {

            val builder = NotificationCompat.Builder(this@MainActivity,channelId)
            builder.setSmallIcon(R.drawable.kotlin)
                .setContentTitle("Action Buttons")
                .setAutoCancel(true)
                .setStyle(NotificationCompat.BigTextStyle().bigText("Click to visit google"))
                .setDefaults(NotificationCompat.DEFAULT_ALL)

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://search.yahoo.com/search?fr=mcafee&type=E211US714G0&p=try+kotlin"))
            val pendingIntent = PendingIntent.getActivity(this@MainActivity,0,intent,0)
            builder.addAction(android.R.drawable.btn_default,"VIEW",pendingIntent)
            val path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            builder.setSound(path)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                val channel = NotificationChannel(channelId,"Title",NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
                builder.setChannelId(channelId)
            }
            notificationManager.notify(NOTIFICATION,builder.build())
        }
    }
}