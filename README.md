# **Guia de Comandos do Makefile**

Este documento descreve os comandos disponíveis no **Makefile** para automação do projeto.

## **Pré-requisitos**

### **1. Docker e Docker Compose**
Para rodar o comando `start-api`, é necessário:
- Docker e Docker Compose instalados.
- Adicionar o usuário ao grupo Docker para evitar o uso do `sudo`.

**Passos:**
1. Instalar Docker Compose:
   ```bash
   sudo apt install docker-compose
   ```
2. Adicionar o usuário ao grupo Docker:
   ```bash
   sudo usermod -aG docker $USER
   ```
3. Reiniciar a sessão do terminal:
   ```bash
   newgrp docker
   ```

### **2. Biblioteca Make**
Para executar o Makefile no Linux:
```bash
sudo apt install make
```

### **3. Maven Wrapper (mvnw)**
Caso ocorra problemas de permissão ou conversão com o `mvnw`:
```bash
sudo apt install dos2unix
```
Convertendo o arquivo:
```bash
dos2unix mvnw
chmod +x mvnw
```

---

## **Comandos**

### **1. start-api**
Sobe o banco de dados MongoDB usando Docker Compose e inicia a aplicação Spring Boot.

**Uso:**
```bash
make start-api
```

**Descrição:**
1. Sobe o MongoDB com **Docker Compose** em modo `detached` (-d).
2. Executa o Maven para limpar o projeto (`clean`) e iniciar a aplicação Spring Boot.

---

### **2. unit-test**
Executa os **testes unitários**.

**Uso:**
```bash
make unit-test
```

**Descrição:**
- Usa o Maven com o profile `unit-test` para executar testes unitários.
- O comando Maven executado é:
  ```bash
  ./mvnw verify -Punit-test
  ```

---

### **3. integration-test**
Executa os **testes de integração**.

**Uso:**
```bash
make integration-test
```

**Descrição:**
- Usa o Maven com o profile `integration-test` para executar testes de integração.
- O comando Maven executado é:
  ```bash
  ./mvnw verify -Pintegration-test
  ```

---

### **4. system-test**
Executa os **testes de sistema** (ex.: testes BDD com Cucumber).

**Uso:**
```bash
make system-test
```

**Descrição:**
- Usa o Maven com o profile `system-test` para executar testes de sistema.
- O comando Maven executado é:
  ```bash
  ./mvnw test -Psystem-test
  ```

---

### **5. ci-test**
Executa todos os testes sequencialmente, simulando um pipeline de CI (Continuous Integration).

**Uso:**
```bash
make ci-test
```

**Descrição:**
- Executa os seguintes comandos na sequência:
  1. `unit-test`
  2. `integration-test`
  3. `system-test`
- Exibe uma mensagem final indicando que todos os testes foram concluídos com sucesso.

---

### **6. clean**
Realiza a limpeza do projeto e encerra o MongoDB.

**Uso:**
```bash
make clean
```

**Descrição:**
1. Limpa os arquivos gerados pelo Maven (`./mvnw clean`).
2. Encerra os contêineres Docker usados pelo MongoDB (`docker compose down`).
3. Exibe uma mensagem de conclusão da limpeza.

---

## **Fluxo de Comandos Recomendado**

1. Subir o MongoDB e a aplicação Spring Boot:
   ```bash
   make start-api
   ```

2. Executar os testes individualmente:
   - **Testes Unitários**:
     ```bash
     make unit-test
     ```
   - **Testes de Integração**:
     ```bash
     make integration-test
     ```
   - **Testes de Sistema**:
     ```bash
     make system-test
     ```

3. Executar todos os testes como em um CI:
   ```bash
   make ci-test
   ```

4. Limpar o projeto e encerrar os contêineres:
   ```bash
   make clean
   ```

---

## **Gerar Relatório de Cobertura com Jacoco**

O **Jacoco** gera relatórios de cobertura de testes automaticamente após a execução dos testes.

### **Gerando o Relatório**
1. Execute os testes:
   ```bash
   make ci-test
   ```
2. O **Jacoco** irá gerar o relatório de cobertura automaticamente no seguinte diretório:
   ```
   target/site/jacoco/index.html
   ```

### **Visualizando o Relatório**
Para visualizar o relatório no navegador, execute o comando:

**Linux**:
```bash
xdg-open target/site/jacoco/index.html
```

**Windows (WSL):**
```bash
explorer.exe target/site/jacoco/index.html
```

**Outros Sistemas:**
- Navegue manualmente até o diretório `target/site/jacoco/` e abra o arquivo `index.html`.

### **Estrutura do Relatório**
- **Instruções**: Percentual de instruções executadas.
- **Branches**: Percentual de branches (condições) cobertas.
- **Linhas**: Percentual de linhas de código cobertas.

---

## **Execução de Teste de Performance com JMeter**

### **Pré-requisitos**
- **JMeter** instalado.

### **Execução do Teste**
1. Navegue até a pasta onde está o JMeter:
   ```bash
   cd /caminho/para/apache-jmeter-5.6.3
   ```
2. Execute o teste de performance usando o arquivo `.jmx`:
   ```bash
   ./bin/jmeter -n -t /caminho/para/test_performance.jmx -l resultados.jtl -e -o /caminho/para/reports
   ```
   - **`-n`**: Executa em modo não-GUI (headless).
   - **`-t`**: Caminho para o arquivo de teste `.jmx`.
   - **`-l`**: Gera um arquivo de log com os resultados.
   - **`-e` e `-o`**: Gera um relatório HTML na pasta especificada.

### **Visualização do Relatório**
1. Após executar o teste, acesse o relatório HTML gerado no diretório especificado:
   ```bash
   xdg-open /caminho/para/reports/index.html
   ```
   - Em sistemas WSL:
     ```bash
     explorer.exe /caminho/para/reports/index.html
     ```

### **Resultados**
O relatório inclui:
- **APDEX**: Índice de desempenho com base nos tempos de resposta.
- **Resumo de Solicitações**: Percentual de aprovações e falhas.
- **Estatísticas**: Detalhes sobre tempos de resposta, erros e taxa de transferência.

---

### **Exemplo de Fluxo Completo**
```bash
make clean
make start-api
make ci-test
# Gerar e visualizar o relatório de cobertura
xdg-open target/site/jacoco/index.html

# Executar teste de performance
./bin/jmeter -n -t /caminho/para/test_performance.jmx -l resultados.jtl -e -o /caminho/para/reports
xdg-open /caminho/para/reports/index.html
```
