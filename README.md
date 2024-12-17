---
# **Guia de Comandos do Makefile**

Este documento descreve os comandos disponíveis no **Makefile** para automação do projeto.

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

