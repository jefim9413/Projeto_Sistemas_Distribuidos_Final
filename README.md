# 📌 Projeto de Sistemas Distribuídos - Cliente/Servidor UDP

Este projeto implementa um **serviço remoto distribuído** baseado na arquitetura **cliente-servidor** e **modelo de requisição-resposta**, utilizando **sockets UDP** e **serialização JSON**.

---

## 🔹 **Arquitetura do Sistema**
A comunicação entre Cliente e Servidor segue o modelo de **chamada remota a métodos (RMI)**. Cada requisição segue este fluxo:

1️⃣ **O ClienteUDP** 🖥️ serializa a requisição em JSON e a envia para o servidor via **UDP**.  
2️⃣ **O ServidorUDP** 📡 recebe a mensagem e a passa para o **Despachante**.  
3️⃣ **O Despachante** 🛠️ identifica o método correto e repassa os argumentos ao **Servente**.  
4️⃣ **O Servente** 🏗️ executa o cálculo e retorna o resultado ao servidor.  
5️⃣ **O ServidorUDP** 🔁 reenvia a resposta processada para o **ClienteUDP**.  
6️⃣ **O ClienteUDP** 🎯 exibe o resultado no terminal.  

---

## 🚀 **Funcionalidades**
✔ **Comunicação Cliente-Servidor via UDP**  
✔ **Protocolo de requisição-resposta**  
✔ **Mensagens empacotadas usando JSON**  
✔ **Arquitetura baseada em Proxy, Esqueleto e Despachante**  
✔ **Três métodos remotos implementados**  
✔ **Tratamento de falhas UDP (Timeout e retransmissão)**  
✔ **Prevenção de requisições duplicadas**  

---

## 📌 **Métodos Remotos Implementados**

### 🔹 `calcularSoma(List<Integer> numeros)`
📌 **Descrição:** Retorna a soma de uma lista de números inteiros.  
📌 **Exemplo de Entrada JSON:** `{ "numeros": [10, 20, 30] }`  
📌 **Exemplo de Resposta:** `"Resultado da soma: 60"`  

### 🔹 `calcularMedia(List<Integer> numeros)`
📌 **Descrição:** Retorna a média dos valores da lista.  
📌 **Exemplo de Entrada JSON:** `{ "numeros": [10, 20, 30, 40] }`  
📌 **Exemplo de Resposta:** `"Resultado da média: 25.0"`  

### 🔹 `resolverEquacaoSegundoGrau(int a, int b, int c)`
📌 **Descrição:** Resolve uma equação do segundo grau na forma `ax² + bx + c = 0`.  
📌 **Exemplo de Entrada JSON:** `{ "a": 1, "b": -3, "c": 2 }`  
📌 **Exemplo de Resposta:** `"Raízes da equação: x1=2, x2=1"`  

---

## ⚙️ **Como Compilar e Executar**

Aqui estão os passos para **compilar e executar** corretamente o projeto.

### **1️⃣ Compilar o Código**  
Antes de compilar, certifique-se de que a biblioteca **Gson** (`gson-2.8.9.jar`) está na pasta `libs/`.

🔹 **Para compilar todos os arquivos do projeto, use o comando abaixo:**  
```sh
javac -cp "libs/gson-2.8.9.jar" -d out \
src/com/distribuidos/model/Mensagem.java \
src/com/distribuidos/model/Envelope.java \
src/com/distribuidos/server/ServidorUDP.java \
src/com/distribuidos/server/Despachante.java \
src/com/distribuidos/server/Servente.java \
src/com/distribuidos/client/ClienteUDP.java
```

### **2️⃣ Iniciar o Servidor**
🔹 **Após a compilação, inicie o ServidorUDP com o seguinte comando:**
```sh 
java -cp "out;libs/gson-2.8.9.jar" com.distribuidos.server.ServidorUDP
```

### **3️⃣ Executar o Cliente**
🔹 **Com o servidor rodando, execute o ClienteUDP para enviar requisições:**

```` sh
java -cp "out;libs/gson-2.8.9.jar" com.distribuidos.client.ClienteUDP
````