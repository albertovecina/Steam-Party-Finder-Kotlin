package com.vsa.steampartyfinder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.vsa.steampartyfinder.data.source.usecase.GetFriendsUseCase
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GetFriendsUseCase().getSteamFriendSummariesByNickName("Asmel")
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: String) {
                        test.text = t
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", e.message)
                    }
                })
    }
}
