package mx.edu.itson.asignacion4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pesoK: TextView = findViewById(R.id.peso)
        val alturaK: TextView = findViewById(R.id.estatura)
        val imc: TextView = findViewById(R.id.imc)
        val rango: TextView = findViewById(R.id.range)
        val calcular: Button = findViewById(R.id.calcular)

        calcular.setOnClickListener {
            var peso: Double = 0.0
            var estatura: Double = 0.0

            try{
                peso = pesoK.text.toString().toDouble()
                estatura = alturaK.text.toString().toDouble()
            }catch (e: java.lang.Exception){
                imc.setText("Debe ingresar valores reales")
                println(e)
            }

            var resultado = calcularIMC(estatura,peso)
            val formattedNumber = "%.2f".format(resultado)
            imc.setText(formattedNumber)

            var salud: String
            var color: Int

            when{
                resultado < 18.5 -> {
                    salud ="Bajo Peso"
                    color = R.color.red
                }
                resultado >= 18.5 && resultado <= 24.9 -> {
                    salud ="Saludable"
                    color = R.color.greenish
                }
                resultado >= 25 && resultado <= 29.9 -> {
                    salud ="Sobrepeso"
                    color = R.color.yellow
                }
                resultado >= 30 && resultado <= 34.9 -> {
                    salud ="Obesidad grado 1"
                    color = R.color.orange
                }
                resultado >= 35 && resultado <= 39.9 -> {
                    salud ="Obesidad grado 2"
                    color = R.color.brown
                }
                resultado >= 40 -> {
                    salud ="Obesidad grado 3"
                    color = R.color.red
                }
                else -> {
                    salud ="Error"
                    color = 0
                }
            }

            rango.setBackgroundResource(color)
            rango.setText(salud)
        }

    }

    private fun calcularIMC(estatura: Double, peso: Double): Double {
        return peso/(estatura*estatura)
    }
}