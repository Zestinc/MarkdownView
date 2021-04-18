package br.tiagohm.markdownview.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import br.tiagohm.markdownview.MarkdownView
import br.tiagohm.markdownview.app.MainActivity.MyBean.DiasDaSemana
import br.tiagohm.markdownview.css.InternalStyleSheet
import br.tiagohm.markdownview.css.styles.Github

class MainActivity : AppCompatActivity() {
    private var mMarkdownView: MarkdownView? = null
    private val mStyle: InternalStyleSheet = Github()
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_theme_action -> {
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Cria o bean.
        val myBean = MyBean()
        myBean.hello = "Olá"
        myBean.diasDaSemana = DiasDaSemana.DOMINGO
        mMarkdownView = findViewById(R.id.mark_view)
        mMarkdownView?.addStyleSheet(mStyle)
        //http://stackoverflow.com/questions/6370690/media-queries-how-to-target-desktop-tablet-and-mobile
        mStyle.addMedia("screen and (min-width: 320px)")
        mStyle.addRule("h1", "color: green")
        mStyle.endMedia()
        mStyle.addMedia("screen and (min-width: 481px)")
        mStyle.addRule("h1", "color: red")
        mStyle.endMedia()
        mStyle.addMedia("screen and (min-width: 641px)")
        mStyle.addRule("h1", "color: blue")
        mStyle.endMedia()
        mStyle.addMedia("screen and (min-width: 961px)")
        mStyle.addRule("h1", "color: yellow")
        mStyle.endMedia()
        mStyle.addMedia("screen and (min-width: 1025px)")
        mStyle.addRule("h1", "color: gray")
        mStyle.endMedia()
        mStyle.addMedia("screen and (min-width: 1281px)")
        mStyle.addRule("h1", "color: orange")
        mStyle.endMedia()
        mMarkdownView?.setBean(myBean)

        val intent = intent
        val uri = intent.data

        if (uri != null) {
            mMarkdownView?.loadMarkdownFromContentUri(uri)
        } else {
            mMarkdownView?.loadMarkdownFromAsset("markdown1.md")
        }
    }

    class MyBean {
        enum class DiasDaSemana {
            DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO
        }

        var hello: String? = null
        var diasDaSemana: DiasDaSemana? = null

    }
}