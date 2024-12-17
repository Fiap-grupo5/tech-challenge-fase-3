# Variáveis
MVN_ARGS := -Dorg.slf4j.simpleLogger.showDateTime=true \
			-Dorg.slf4j.simpleLogger.dateTimeFormat="yyyy-MM-dd HH:mm:ss.SSS" \
			-Dorg.slf4j.simpleLogger.showLogName=false \
			-Dorg.slf4j.simpleLogger.showShortLogName=false \
			-Dorg.slf4j.simpleLogger.showThreadName=false 

TIMESTAMP := $(shell date +'%F %T')


start-api:
	@echo "$(TIMESTAMP) [INFO] Subindo o MongoDB com Docker Compose..."
	@docker compose up -d
	@echo "$(TIMESTAMP) [INFO] Iniciando a aplicação Spring Boot..."
	@./mvnw $(MVN_ARGS) clean spring-boot:run

# Testes Unitários
unit-test:
	@echo "$(TIMESTAMP) [INFO] Executando testes unitários..."
	@./mvnw $(MVN_ARGS) verify -Punit-test

# Testes de Integração
integration-test:
	@echo "$(TIMESTAMP) [INFO] Executando testes de integração..."
	@./mvnw $(MVN_ARGS) verify -Pintegration-test

# Testes de Sistema
system-test:
	@echo "$(TIMESTAMP) [INFO] Executando testes de sistema..."
	@./mvnw $(MVN_ARGS) test -Psystem-test

# Executar todos os testes sequencialmente (simulando CI)
ci-test: unit-test integration-test system-test
	@echo "$(TIMESTAMP) [INFO] Todos os testes foram executados com sucesso."

# Limpeza
clean:
	@echo "$(TIMESTAMP) [INFO] Limpando o projeto..."
	@./mvnw clean
	@docker compose down
	@echo "$(TIMESTAMP) [INFO] Limpeza concluída."

