# 📡 **Analizador de señales y espectros**

🎓 _Facultad de Ciencias Exactas y Naturales y Agrimensura_  
📘 _Cátedra: Comunicaciones de Datos_  
📅 _Año: 2025_

---

## ✨ **📌 Introducción**

En el campo de las comunicaciones de datos, las señales electromagnéticas son el
medio fundamental mediante el cual se transmite información de distintos tipos: voz,
video, imágenes o datos. 

---

## 🔁 **Tipos de señales**

- 🔉 **Señal Analógica (Continua):**  
  Varía suavemente en el tiempo, sin saltos.

- 💡 **Señal Digital (Discreta):**  
  Permanece constante por intervalos y luego cambia a otro valor constante.

En ambos casos están compuestas por una combinación de frecuencias que definen su
comportamiento en el tiempo y en el espectro de frecuencias.
Por esta razón, resulta esencial comprender no solo su forma en el dominio del tiempo,
sino también su composición en el dominio de la frecuencia, donde se analizan
aspectos como el ancho de banda y el espectro de la señal.

---

## 📊 **Espectro de una señal**

El espectro de una señal representa la distribución de las diferentes frecuencias
presentes en esa señal. Este concepto es fundamental en el análisis de señales, ya que
permite comprender cómo se distribuye la energía de la señal a lo largo del dominio de
las frecuencias.

##  📏 **Ancho de banda** 

Representa el rango de frecuencias que contiene una señal. Cuanto mayor sea el ancho
de banda, mayor es la capacidad de una señal para transportar información. Sin
embargo, los medios de transmisión tienen limitaciones físicas que restringen este
ancho de banda, lo cual puede provocar distorsiones y pérdidas de información.

---

## 🧠 **Teorema de Muestreo (Nyquist-Shannon)**

🔢 Para evitar pérdida de información al digitalizar una señal:

**Fm > 2 × Fmax**

Donde Fmax es la máxima frecuencia presente en la señal. Si esta condición no se
cumple, ocurre el fenómeno de aliasing, donde frecuencias altas se confunden con más
bajas en el espectro resultante.

---

## 🔬 **Transformada de Fourier (FFT)**

Convierte señales del **dominio del tiempo** al **dominio de la frecuencia**.  
🔍 Permite conocer las **frecuencias que componen una señal**.  

---

## 🖥️ **Representación de señales en MATLAB**

Se incluyen scripts para representar:

-  Señal Escalón  
-  Señal Pulso  
-  Señal Sampling  
-  Señal Sinc  
-  Señal Impulso  
-  Señal Triangular  
-  Señal Diente de Sierra  
-  Señal Exponencial  
-  Señal Cuadrada
