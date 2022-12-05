package com.nascimentofe.googledevelopercertification.presentation.ui.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import com.nascimentofe.googledevelopercertification.R
import com.nascimentofe.googledevelopercertification.databinding.FragmentNotificationBinding

private const val NOTIFICATION_ID = 0
private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
private const val ACTION_UPDATE = "ACTION_UPDATE_NOTIFICATION"
private const val ACTION_CANCEL = "ACTION_CANCEL_NOTIFICATION"
private const val ACTION_DELETE_ALL = "ACTION_DELETED_NOTIFICATION"

class NotificationFragment : Fragment() {

    private val binding: FragmentNotificationBinding by lazy {
        FragmentNotificationBinding.inflate(layoutInflater)
    }
    private lateinit var notificationManager: NotificationManager
    private var notificationReceiver = NotificationReceiver()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        notificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        setupButtonListener()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createLatestNotification()
        }
        registerNotificationReceiver()
        setupStateButton(enableNotify = true, enableUpdate = false, enableCancel = false)

        return binding.root
    }

    private fun setupStateButton(enableNotify: Boolean, enableUpdate: Boolean, enableCancel: Boolean) {
        binding.btnNotify.isEnabled = enableNotify
        binding.btnUpdate.isEnabled = enableUpdate
        binding.btnCancel.isEnabled = enableCancel
    }

    private fun setupButtonListener() {

        binding.btnNotify.setOnClickListener {
            sendNotification()
        }

        binding.btnUpdate.setOnClickListener {
            val androidImage = BitmapFactory.decodeResource(resources, R.drawable.ic_message)

            val notification = getNotificationBuilder()
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(androidImage)
                        .setBigContentTitle("Notificacao atualizada!")
                )

            with(notificationManager) {
                notify(NOTIFICATION_ID, notification.build())
            }

            setupStateButton(enableNotify = false, enableUpdate = false, enableCancel = true)
        }

        binding.btnCancel.setOnClickListener {
            with(notificationManager) {
                cancel(NOTIFICATION_ID)
            }
            setupStateButton(enableNotify = true, enableUpdate = false, enableCancel = false)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createLatestNotification() {
        val notificationChannel = NotificationChannel(
            PRIMARY_CHANNEL_ID, "Felipe Notification", NotificationManager.IMPORTANCE_HIGH
        )

        with(notificationChannel) {
            enableVibration(true)
            description = "Notification from Felipe"
            enableLights(true)
            lightColor = Color.BLUE
        }

        with(notificationManager) {
            createNotificationChannel(notificationChannel)
        }
    }

    private fun getNotificationBuilder() : NotificationCompat.Builder {
        val intent = Intent(requireContext(), NotificationFragment::class.java)
        val pendingIntent = PendingIntent.getActivity(
            requireContext(), NOTIFICATION_ID,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)

        return NotificationCompat
            .Builder(requireContext(), PRIMARY_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_message)
            .setContentTitle("Titulo da notificacao")
            .setContentText("Texto da notificacao")
            .setSmallIcon(R.drawable.ic_warning)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(false)
    }

    private fun sendNotification() {
        val builder = getNotificationBuilder()

        createNotificationAction(builder, NOTIFICATION_ID, ACTION_UPDATE)
        createNotificationAction(builder, NOTIFICATION_ID, ACTION_CANCEL)

        val deleteAllAction = Intent(ACTION_DELETE_ALL)
        val deletedAction = PendingIntent.getBroadcast(
            requireContext(),
            NOTIFICATION_ID,
            deleteAllAction,
            PendingIntent.FLAG_ONE_SHOT
        )
        builder.setDeleteIntent(deletedAction)

        with(notificationManager) {
            notify(NOTIFICATION_ID, builder.build())
        }

        setupStateButton(enableNotify = false, enableUpdate = true, enableCancel = true)
    }

    private fun registerNotificationReceiver() {
        val notifAcFilters = IntentFilter()
        with(notifAcFilters) {
            addAction(ACTION_UPDATE)
            addAction(ACTION_DELETE_ALL)
            addAction(ACTION_CANCEL)
        }
        requireActivity().registerReceiver(notificationReceiver, notifAcFilters)
    }

    private fun createNotificationAction(
        builder: NotificationCompat.Builder,
        notificationId: Int,
        action: String
    ) {

        val updateActionFilter = Intent(action)
        val updateAction = PendingIntent.getBroadcast(
            requireContext(),
            notificationId,
            updateActionFilter,
            PendingIntent.FLAG_ONE_SHOT
        )

        builder.addAction(
            R.drawable.ic_favorite,
            if (action == ACTION_UPDATE) ACTION_UPDATE else ACTION_CANCEL,
            updateAction
        )
    }

    override fun onDestroy() {
        requireActivity().unregisterReceiver(notificationReceiver)
        super.onDestroy()
    }

    class NotificationReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {

        }

    }
}