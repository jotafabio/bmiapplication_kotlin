package pl.edu.pwr.labX.i251937.bmicalculator

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode
import kotlin.math.absoluteValue
import kotlin.math.roundToLong
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cmdCalculate.setOnClickListener() {
            txtHeight.invalidate()
            txtWeight.invalidate()

            var dHeight: Double? = txtHeight.text.toString().toDouble()
            var dWeight: Double? = txtWeight.text.toString().toDouble()
            var calculatedBMI: Double = calculateBMI(dHeight, dWeight)
            var displayBMI: String = calculatedBMI.toString().take(5)
            var bmiColor: Int = Color.BLACK
            var bmiClass: String = ""

            if (calculatedBMI <= 18.5) {
                bmiColor = Color.GRAY
                bmiClass = "Underweight"
            }
            if (calculatedBMI > 18.5 && calculatedBMI < 25.0) {
                bmiColor = Color.GREEN
                bmiClass = "Normal"
            }
            if (calculatedBMI >= 25.0 && calculatedBMI < 29.9) {
                bmiColor = Color.MAGENTA
                bmiClass = "Overweight"
            }
            if (calculatedBMI >= 29.9) {
                bmiColor = Color.RED
                bmiClass = "Obese"
            }
            lblBMIValue.text = "Your BMI is $displayBMI ($bmiClass)"
            lblBMIValue.setTextColor(bmiColor)
        }

        cmdAbout.setOnClickListener(){
            var builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("About BMI Calculator")
            builder.setMessage("App prepared by Jose Fabio Ribeiro Bezerra for PWR W8 MAW Course")
            builder.setNeutralButton("Cancel"){_,_->Toast.makeText(applicationContext,"See you!",Toast.LENGTH_SHORT)}
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}

private fun calculateBMI(dHeight: Double?, dWeight: Double?): Double {
    var dHeightPrivate: Double
    var dWeightPrivate: Double

    return if (dHeight != null && dWeight != null) {
        dHeightPrivate = dHeight
        dWeightPrivate = dWeight
        (dWeightPrivate / dHeightPrivate / dHeightPrivate) *10000
    } else {
        0.0
    }
}

