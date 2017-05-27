package cn.xiaoniaojun.secondhandtoy.mvvm.V.ui.activity

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Transformation
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import cn.xiaoniaojun.secondhandtoy.R
import it.sephiroth.android.library.easing.Back
import it.sephiroth.android.library.easing.EasingManager

class LoginActivity : AppCompatActivity() {
    private var rootLayout: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main_login)

        rootLayout = findViewById(R.id.main_container) as ViewGroup

        val email = findViewById(R.id.email) as EditText
        val password = findViewById(R.id.password) as EditText

        val emailS = findViewById(R.id.email_singup) as EditText
        val passwordS = findViewById(R.id.password_singup) as EditText
        val passwordC = findViewById(R.id.password_confirm) as EditText

        val tvLogin = findViewById(R.id.login_tv) as TextView

        tvLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }


        // 为输入框聚焦事件设置动画
        val focuslistene = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                animateOnFocus(v)
            } else {
                animateOnFocusLost(v)
            }
        }

        email.onFocusChangeListener = focuslistene
        password.onFocusChangeListener = focuslistene
        emailS.onFocusChangeListener = focuslistene
        passwordS.onFocusChangeListener = focuslistene
        passwordC.onFocusChangeListener = focuslistene
    }

    fun showSingUp(view: View) {
        val animationCircle = findViewById(R.id.animation_circle) as CardView
        val animationFirstArist = findViewById(R.id.animation_first_arist)
        val animationSecondArist = findViewById(R.id.animation_second_arist)
        val animationSquare = findViewById(R.id.animation_square)
        val squareParent = animationSquare.parent as LinearLayout
        val animationTV = findViewById(R.id.animation_tv) as TextView
        val twitterImageView = findViewById(R.id.twitter_img) as ImageView
        val instagramImageView = findViewById(R.id.instagram_img) as ImageView
        val facebokImageView = findViewById(R.id.facebook_img) as ImageView
        val singupFormContainer = findViewById(R.id.signup_form_container)
        val loginFormContainer = findViewById(R.id.login_form_container)
        val backgroundColor = ContextCompat.getColor(this, R.color.colorPrimary)
        val singupTV = findViewById(R.id.singup_big_tv) as TextView

        val scale = resources.displayMetrics.density

        val circle_curr_margin = (82 * scale + 0.5f).toInt()
        val circle_target_margin = rootLayout!!.width - (70 * scale + 0.5f).toInt()

        val first_curr_width = (120 * scale + 0.5f).toInt()
        val first_target_width = (rootLayout!!.height * 1.3).toInt()

        val first_curr_height = (70 * scale + 0.5f).toInt()
        val first_target_height = rootLayout!!.width

        val first_curr_margin = (40 * scale + 0.5f).toInt()
        val first_target_margin = (35 * scale + 0.5f).toInt()
        val first_expand_margin = first_curr_margin - first_target_height

        val square_target_width = rootLayout!!.width
        val square_target_height = (80 * scale + 0.5f).toInt()

        val tv_curr_x = findViewById(R.id.singup_tv).x + findViewById(R.id.singup_button).x
        val tv_curr_y = findViewById(R.id.singup_tv).y + findViewById(R.id.buttons_container).y + findViewById(R.id.singup_container).y

        val tv_target_x = findViewById(R.id.singup_big_tv).x
        val tv_target_y = findViewById(R.id.singup_big_tv).y

        val tv_curr_size = 16f
        val tv_target_size = 56f

        val tv_curr_color = Color.parseColor("#ffffff")
        val tv_target_color = Color.parseColor("#ccffffff")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccentDark)
        }

        squareParent.gravity = Gravity.END
        animationTV.setText(R.string.sign_up)

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                var diff_margin = circle_curr_margin - circle_target_margin
                var margin = circle_target_margin + (diff_margin - diff_margin * interpolatedTime).toInt()

                val params_circle = animationCircle.layoutParams as RelativeLayout.LayoutParams
                params_circle.setMargins(0, 0, margin, (40 * scale + 0.5f).toInt())
                animationCircle.requestLayout()

                //----------------------------------------------------------
                // 1号变换块
                // 宽度变为高度
                val diff_width = first_curr_width - first_target_width
                val width = first_target_width + (diff_width - diff_width * interpolatedTime).toInt()

                val diff_height = first_curr_height - first_target_height
                val height = first_target_height + (diff_height - (diff_height - first_target_margin) * interpolatedTime).toInt()

                diff_margin = first_curr_margin - first_expand_margin
                margin = first_expand_margin + (diff_margin - diff_margin * interpolatedTime).toInt()
                val margin_r = (-(first_target_width - rootLayout!!.width) * interpolatedTime).toInt()

                val params_first = animationFirstArist.layoutParams as RelativeLayout.LayoutParams
                params_first.setMargins(0, 0, margin_r, margin)
                params_first.width = width
                params_first.height = height
                animationFirstArist.requestLayout()

                animationFirstArist.pivotX = 0f
                animationFirstArist.pivotY = 0f
                animationFirstArist.rotation = -90 * interpolatedTime
                //-----------------------------------------------------------

                margin = first_curr_margin + (first_target_margin * interpolatedTime).toInt()
                val params_second = animationSecondArist.layoutParams as RelativeLayout.LayoutParams
                params_second.setMargins(0, 0, margin_r, margin)
                params_second.width = width
                animationSecondArist.requestLayout()

                animationSecondArist.pivotX = 0f
                animationSecondArist.pivotY = animationSecondArist.height.toFloat()
                animationSecondArist.rotation = 90 * interpolatedTime

                animationSquare.layoutParams.width = (square_target_width.toDouble() * interpolatedTime.toDouble() * 1.4).toInt()
                animationSquare.requestLayout()

                val diff_x = tv_curr_x - tv_target_x
                val x = tv_target_x + (diff_x - diff_x * interpolatedTime)
                val diff_y = tv_curr_y - tv_target_y
                val y = tv_target_y + (diff_y - diff_y * interpolatedTime)

                animationTV.x = x
                animationTV.y = y
                animationTV.requestLayout()

                // 变换社交图标颜色
                if (interpolatedTime >= 0.2f && interpolatedTime < 0.3f) {
                    twitterImageView.setImageResource(R.drawable.ic_qq_pink)
                } else if (interpolatedTime >= 0.45f && interpolatedTime < 0.55f) {
                    instagramImageView.setImageResource(R.drawable.ic_wechat_pink)
                } else if (interpolatedTime >= 0.65f && interpolatedTime < 0.75f) {
                    facebokImageView.setImageResource(R.drawable.ic_weibo_pink)
                }

                singupFormContainer.alpha = interpolatedTime
                loginFormContainer.alpha = 1 - interpolatedTime
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {
                findViewById(R.id.singup_container).visibility = View.INVISIBLE
                animationFirstArist.visibility = View.VISIBLE
                animationSecondArist.visibility = View.VISIBLE
                animationSquare.visibility = View.VISIBLE
                animationTV.visibility = View.VISIBLE
                singupFormContainer.visibility = View.VISIBLE

                animationFirstArist.bringToFront()
                squareParent.bringToFront()
                animationSecondArist.bringToFront()
                animationCircle.bringToFront()
                findViewById(R.id.buttons_container).bringToFront()
                singupFormContainer.bringToFront()
                singupTV.bringToFront()
                animationTV.bringToFront()

                animationFirstArist.setBackgroundColor(backgroundColor)
                animationSecondArist.setBackgroundColor(backgroundColor)
                animationCircle.setCardBackgroundColor(backgroundColor)
                animationSquare.setBackgroundColor(backgroundColor)
            }

            override fun onAnimationRepeat(arg0: Animation) {}

            override fun onAnimationEnd(arg0: Animation) {
                Handler().postDelayed({
                    animationFirstArist.visibility = View.GONE
                    animationSecondArist.visibility = View.GONE
                    animationTV.visibility = View.GONE
                    animationSquare.visibility = View.GONE
                    findViewById(R.id.singup_big_tv).visibility = View.VISIBLE
                }, 100)
                rootLayout!!.setBackgroundColor(ContextCompat.getColor(this@LoginActivity, R.color.colorPrimary))
                (animationSquare.parent as View).setBackgroundColor(ContextCompat.getColor(this@LoginActivity, R.color.colorPrimary))
                findViewById(R.id.login_form_container).visibility = View.GONE
                findViewById(R.id.login_tv).visibility = View.GONE
                showLoginButton()
            }
        })

        val a2 = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                animationSquare.layoutParams.height = (square_target_height.toDouble() * interpolatedTime.toDouble() * 1.4).toInt()
                animationSquare.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        val a3 = ValueAnimator.ofFloat(tv_curr_size, tv_target_size)
        a3.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            animationTV.textSize = animatedValue
        }

        val a4 = ValueAnimator.ofInt(tv_curr_color, tv_target_color)
        a4.setEvaluator(ArgbEvaluator())
        a4.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            animationTV.setTextColor(animatedValue)
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val a5 = ValueAnimator.ofInt(Color.argb(255, 249, 164, 221), Color.argb(255, 19, 26, 86))
            a5.setEvaluator(ArgbEvaluator())
            a5.addUpdateListener { animation ->
                val animatedValue = animation.animatedValue as Int
                rootLayout!!.setBackgroundColor(animatedValue)
                (animationSquare.parent as View).setBackgroundColor(animatedValue)
            }
            a5.duration = 400
            a5.start()
        }


        a.duration = 400
        a2.duration = 172
        a3.duration = 400
        a4.duration = 400


        a4.start()
        a3.start()
        animationSquare.startAnimation(a2)
        animationCircle.startAnimation(a)
        singupFormContainer.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_form))
    }

    fun showLogIn(view: View) {
        val animationCircle = findViewById(R.id.animation_circle) as CardView
        val animationFirstArist = findViewById(R.id.animation_first_arist)
        val animationSecondArist = findViewById(R.id.animation_second_arist)
        val animationSquare = findViewById(R.id.animation_square)
        val squareParent = animationSquare.parent as LinearLayout
        val animationTV = findViewById(R.id.animation_tv) as TextView
        val twitterImageView = findViewById(R.id.twitter_img) as ImageView
        val instagramImageView = findViewById(R.id.instagram_img) as ImageView
        val facebokImageView = findViewById(R.id.facebook_img) as ImageView
        val singupFormContainer = findViewById(R.id.signup_form_container)
        val loginFormContainer = findViewById(R.id.login_form_container)
        val loginTV = findViewById(R.id.login_tv) as TextView
        val backgrounColor = ContextCompat.getColor(this, R.color.colorAccent)


        val scale = resources.displayMetrics.density

        val circle_curr_margin = rootLayout!!.width - (view.width.toFloat() - view.x - animationCircle.width.toFloat()).toInt()
        val circle_target_margin = 0

        val first_curr_width = (108 * scale + 0.5f).toInt()
        val first_target_width = rootLayout!!.height * 2

        val first_curr_height = (70 * scale + 0.5f).toInt()
        val first_target_height = rootLayout!!.width

        val first_curr_margin = (40 * scale + 0.5f).toInt()
        val first_target_margin = (35 * scale + 0.5f).toInt()
        val first_expand_margin = first_curr_margin - first_target_height
        val first_curr_margin_r = rootLayout!!.width - first_curr_width


        val square_target_width = rootLayout!!.width
        val square_target_height = (80 * scale + 0.5f).toInt()

        val tv_curr_x = findViewById(R.id.login_small_tv).x + findViewById(R.id.login_button).x
        val tv_curr_y = findViewById(R.id.login_small_tv).y + findViewById(R.id.buttons_container).y + findViewById(R.id.login_container).y

        val tv_target_x = findViewById(R.id.login_tv).x
        val tv_target_y = findViewById(R.id.login_tv).y

        val tv_curr_size = 16f
        val tv_target_size = 56f

        val tv_curr_color = Color.parseColor("#ffffff")
        val tv_target_color = Color.parseColor("#ccffffff")


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }

        squareParent.gravity = Gravity.START
        animationTV.setText(R.string.log_in)

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                var diff_margin = circle_curr_margin - circle_target_margin
                var margin = circle_target_margin + (diff_margin - diff_margin * interpolatedTime).toInt()

                val params_circle = animationCircle.layoutParams as RelativeLayout.LayoutParams
                params_circle.setMargins(0, 0, margin, (40 * scale + 0.5f).toInt())
                animationCircle.requestLayout()


                val diff_width = first_curr_width - first_target_width
                val width = first_target_width + (diff_width - diff_width * interpolatedTime).toInt()

                val diff_height = first_curr_height - first_target_height
                val height = first_target_height + (diff_height - (diff_height - first_target_margin) * interpolatedTime).toInt()

                diff_margin = first_curr_margin - first_expand_margin
                margin = first_expand_margin + (diff_margin - diff_margin * interpolatedTime).toInt()
                val margin_r = first_curr_margin_r - (first_curr_margin_r * interpolatedTime).toInt()
                val margin_l = if (rootLayout!!.width - width < 0) rootLayout!!.width - width else 0

                val params_first = animationFirstArist.layoutParams as RelativeLayout.LayoutParams
                params_first.setMargins(margin_l, 0, margin_r, margin)
                params_first.width = width
                params_first.height = height
                animationFirstArist.requestLayout()

                animationFirstArist.pivotX = animationFirstArist.width.toFloat()
                animationFirstArist.pivotY = 0f
                animationFirstArist.rotation = 90 * interpolatedTime

                margin = first_curr_margin + (first_target_margin * interpolatedTime).toInt()
                val params_second = animationSecondArist.layoutParams as RelativeLayout.LayoutParams
                params_second.setMargins(0, 0, margin_r, margin)
                params_second.width = width
                animationSecondArist.requestLayout()

                animationSecondArist.pivotX = animationSecondArist.width.toFloat()
                animationSecondArist.pivotY = animationSecondArist.height.toFloat()
                animationSecondArist.rotation = -(90 * interpolatedTime)

                animationSquare.layoutParams.width = (square_target_width * interpolatedTime).toInt()
                animationSquare.requestLayout()

                val diff_x = tv_curr_x - tv_target_x
                val x = tv_target_x + (diff_x - diff_x * interpolatedTime)
                val diff_y = tv_curr_y - tv_target_y
                val y = tv_target_y + (diff_y - diff_y * interpolatedTime)

                animationTV.x = x
                animationTV.y = y
                animationTV.requestLayout()

                if (interpolatedTime >= 0.2f && interpolatedTime < 0.3f) {
                    facebokImageView.setImageResource(R.drawable.ic_qq_blue)
                } else if (interpolatedTime >= 0.45f && interpolatedTime < 0.55f) {
                    instagramImageView.setImageResource(R.drawable.ic_wechat_blue)
                } else if (interpolatedTime >= 0.65f && interpolatedTime < 0.75f) {
                    twitterImageView.setImageResource(R.drawable.ic_weibo_blue)
                }

                loginFormContainer.alpha = interpolatedTime
                singupFormContainer.alpha = 1 - interpolatedTime
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {
                animationFirstArist.setBackgroundColor(backgrounColor)
                animationSecondArist.setBackgroundColor(backgrounColor)
                animationSquare.setBackgroundColor(backgrounColor)

                animationFirstArist.visibility = View.VISIBLE
                findViewById(R.id.login_container).visibility = View.INVISIBLE
                animationSecondArist.visibility = View.VISIBLE
                animationSquare.visibility = View.VISIBLE
                animationTV.visibility = View.VISIBLE
                loginFormContainer.visibility = View.VISIBLE
                loginTV.visibility = View.INVISIBLE

                animationFirstArist.bringToFront()
                squareParent.bringToFront()
                animationSecondArist.bringToFront()
                animationCircle.bringToFront()
                findViewById(R.id.buttons_container).bringToFront()
                loginFormContainer.bringToFront()
                loginTV.bringToFront()
                animationTV.bringToFront()
            }

            override fun onAnimationRepeat(arg0: Animation) {}

            override fun onAnimationEnd(arg0: Animation) {
                Handler().postDelayed({
                    animationFirstArist.visibility = View.GONE
                    animationSecondArist.visibility = View.GONE
                    animationTV.visibility = View.GONE
                    animationSquare.visibility = View.GONE
                    findViewById(R.id.login_tv).visibility = View.VISIBLE
                    findViewById(R.id.login_tv).bringToFront()
                }, 100)
                rootLayout!!.setBackgroundColor(ContextCompat.getColor(this@LoginActivity, R.color.colorAccent))
                (animationSquare.parent as View).setBackgroundColor(ContextCompat.getColor(this@LoginActivity, R.color.colorAccent))
                findViewById(R.id.signup_form_container).visibility = View.GONE
                findViewById(R.id.singup_big_tv).visibility = View.GONE
                showSignUpButton()
            }
        })

        val a2 = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                animationSquare.layoutParams.height = (square_target_height * interpolatedTime).toInt()
                animationSquare.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        val a3 = ValueAnimator.ofFloat(tv_curr_size, tv_target_size)
        a3.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            animationTV.textSize = animatedValue
        }

        val a4 = ValueAnimator.ofInt(tv_curr_color, tv_target_color)
        a4.setEvaluator(ArgbEvaluator())
        a4.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            animationTV.setTextColor(animatedValue)
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val a5 = ValueAnimator.ofInt(Color.argb(255, 19, 26, 86), Color.argb(255, 249, 164, 221))
            a5.setEvaluator(ArgbEvaluator())
            a5.addUpdateListener { animation ->
                val animatedValue = animation.animatedValue as Int
                rootLayout!!.setBackgroundColor(animatedValue)
                (animationSquare.parent as View).setBackgroundColor(animatedValue)
            }
            a5.duration = 400
            a5.start()
        }

        a.duration = 400
        a2.duration = 172
        a3.duration = 400
        a4.duration = 400

        a4.start()
        a3.start()
        animationSquare.startAnimation(a2)
        animationCircle.startAnimation(a)
        loginFormContainer.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_form_reverse))
    }

    private fun showLoginButton() {
        val singupButton = findViewById(R.id.singup_button) as CardView
        val loginButton = findViewById(R.id.login_button)

        loginButton.visibility = View.VISIBLE
        loginButton.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        findViewById(R.id.login_container).visibility = View.VISIBLE

        val scale = resources.displayMetrics.density
        val curr_singup_margin = (-35 * scale + 0.5f).toInt()
        val target_singup_margin = -singupButton.width

        val curr_login_margin = -loginButton.measuredWidth
        val target_login_margin = (-35 * scale + 0.5f).toInt()

        val manager = EasingManager(object : EasingManager.EasingCallback {

            override fun onEasingValueChanged(value: Double, oldValue: Double) {
                var diff_margin = curr_singup_margin - target_singup_margin
                var margin = target_singup_margin + (diff_margin - diff_margin * value).toInt()

                var layoutParams = singupButton.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(0, 0, margin, 0)
                singupButton.requestLayout()

                diff_margin = curr_login_margin - target_login_margin
                margin = target_login_margin + (diff_margin - diff_margin * value).toInt()

                layoutParams = loginButton.layoutParams as LinearLayout.LayoutParams
                layoutParams.leftMargin = margin
                loginButton.requestLayout()
            }

            override fun onEasingStarted(value: Double) {
                var diff_margin = curr_singup_margin - target_singup_margin
                var margin = target_singup_margin + (diff_margin - diff_margin * value).toInt()

                var layoutParams = singupButton.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(0, 0, margin, 0)
                singupButton.requestLayout()

                diff_margin = curr_login_margin - target_login_margin
                margin = target_login_margin + (diff_margin - diff_margin * value).toInt()

                layoutParams = loginButton.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(margin, 0, 0, 0)
                loginButton.requestLayout()
            }

            override fun onEasingFinished(value: Double) {
                var layoutParams = singupButton.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(0, 0, target_singup_margin, 0)
                singupButton.requestLayout()


                layoutParams = loginButton.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(target_login_margin, 0, 0, 0)
                loginButton.requestLayout()
                singupButton.visibility = View.GONE
            }
        })

        manager.start(Back::class.java, EasingManager.EaseType.EaseOut, 0.0, 1.0, 600)
    }

    private fun showSignUpButton() {
        val singupButton = findViewById(R.id.singup_button) as CardView
        val loginButton = findViewById(R.id.login_button)

        singupButton.visibility = View.VISIBLE
        singupButton.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        findViewById(R.id.singup_container).visibility = View.VISIBLE

        val scale = resources.displayMetrics.density
        val curr_singup_margin = -singupButton.width
        val target_singup_margin = (-35 * scale + 0.5f).toInt()

        val curr_login_margin = (-35 * scale + 0.5f).toInt()
        val target_login_margin = -loginButton.measuredWidth

        val manager = EasingManager(object : EasingManager.EasingCallback {

            override fun onEasingValueChanged(value: Double, oldValue: Double) {
                var diff_margin = curr_singup_margin - target_singup_margin
                var margin = target_singup_margin + (diff_margin - diff_margin * value).toInt()

                var layoutParams = singupButton.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(0, 0, margin, 0)
                singupButton.requestLayout()

                diff_margin = curr_login_margin - target_login_margin
                margin = target_login_margin + (diff_margin - diff_margin * value).toInt()

                layoutParams = loginButton.layoutParams as LinearLayout.LayoutParams
                layoutParams.leftMargin = margin
                loginButton.requestLayout()
            }

            override fun onEasingStarted(value: Double) {
                var diff_margin = curr_singup_margin - target_singup_margin
                var margin = target_singup_margin + (diff_margin - diff_margin * value).toInt()

                var layoutParams = singupButton.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(0, 0, margin, 0)
                singupButton.requestLayout()

                diff_margin = curr_login_margin - target_login_margin
                margin = target_login_margin + (diff_margin - diff_margin * value).toInt()

                layoutParams = loginButton.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(margin, 0, 0, 0)
                loginButton.requestLayout()
            }

            override fun onEasingFinished(value: Double) {
                var layoutParams = singupButton.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(0, 0, target_singup_margin, 0)
                singupButton.requestLayout()


                layoutParams = loginButton.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(target_login_margin, 0, 0, 0)
                loginButton.requestLayout()
                loginButton.visibility = View.GONE
            }
        })

        manager.start(Back::class.java, EasingManager.EaseType.EaseOut, 0.0, 1.0, 600)
    }

    private fun animateOnFocus(v: View) {
        val first_container = v.parent as CardView
        val second_container = first_container.parent as CardView

        val first_curr_radius = resources.getDimension(R.dimen.first_card_radius).toInt()
        val first_target_radius = resources.getDimension(R.dimen.first_card_radius_on_focus).toInt()

        val second_curr_radius = resources.getDimension(R.dimen.second_card_radius).toInt()
        val second_target_radius = resources.getDimension(R.dimen.second_card_radius_on_focus).toInt()

        val first_curr_color = ContextCompat.getColor(this, android.R.color.transparent)
        val first_target_color = (rootLayout!!.background as ColorDrawable).color

        val second_curr_color = ContextCompat.getColor(this, R.color.backgroundEditText)
        val second_target_color = ContextCompat.getColor(this, android.R.color.white)

        val first_anim = ValueAnimator()
        first_anim.setIntValues(first_curr_color, first_target_color)
        first_anim.setEvaluator(ArgbEvaluator())
        first_anim.addUpdateListener { animation -> first_container.setCardBackgroundColor(animation.animatedValue as Int) }

        val second_anim = ValueAnimator()
        second_anim.setIntValues(second_curr_color, second_target_color)
        second_anim.setEvaluator(ArgbEvaluator())
        second_anim.addUpdateListener { animation -> second_container.setCardBackgroundColor(animation.animatedValue as Int) }

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                var diff_radius = first_curr_radius - first_target_radius
                var radius = first_target_radius + (diff_radius * (1 - interpolatedTime)).toInt()

                first_container.radius = radius.toFloat()
                first_container.requestLayout()

                diff_radius = second_curr_radius - second_target_radius
                radius = second_target_radius + (diff_radius * (1 - interpolatedTime)).toInt()

                second_container.radius = radius.toFloat()
                second_container.requestLayout()

            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = 200
        first_anim.duration = 200
        second_anim.duration = 200

        first_anim.start()
        second_anim.start()
        first_container.startAnimation(a)
    }

    private fun animateOnFocusLost(v: View) {
        val first_container = v.parent as CardView
        val second_container = first_container.parent as CardView

        val first_curr_radius = resources.getDimension(R.dimen.first_card_radius_on_focus).toInt()
        val first_target_radius = resources.getDimension(R.dimen.first_card_radius).toInt()

        val second_curr_radius = resources.getDimension(R.dimen.second_card_radius_on_focus).toInt()
        val second_target_radius = resources.getDimension(R.dimen.second_card_radius).toInt()

        val first_curr_color = (rootLayout!!.background as ColorDrawable).color
        val first_target_color = ContextCompat.getColor(this, android.R.color.transparent)

        val second_curr_color = ContextCompat.getColor(this, android.R.color.white)
        val second_target_color = ContextCompat.getColor(this, R.color.backgroundEditText)

        val first_anim = ValueAnimator()
        first_anim.setIntValues(first_curr_color, first_target_color)
        first_anim.setEvaluator(ArgbEvaluator())
        first_anim.addUpdateListener { animation -> first_container.setCardBackgroundColor(animation.animatedValue as Int) }

        val second_anim = ValueAnimator()
        second_anim.setIntValues(second_curr_color, second_target_color)
        second_anim.setEvaluator(ArgbEvaluator())
        second_anim.addUpdateListener { animation -> second_container.setCardBackgroundColor(animation.animatedValue as Int) }

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                var diff_radius = first_curr_radius - first_target_radius
                var radius = first_target_radius + (diff_radius - diff_radius * interpolatedTime).toInt()

                first_container.radius = radius.toFloat()
                first_container.requestLayout()

                diff_radius = second_curr_radius - second_target_radius
                radius = second_target_radius + (diff_radius - diff_radius * interpolatedTime).toInt()

                second_container.radius = radius.toFloat()
                second_container.requestLayout()

            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = 200
        first_anim.duration = 200
        second_anim.duration = 200

        first_anim.start()
        second_anim.start()
        first_container.startAnimation(a)

    }

}
