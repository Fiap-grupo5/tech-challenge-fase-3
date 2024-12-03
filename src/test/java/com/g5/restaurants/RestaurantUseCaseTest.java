package com.g5.restaurants;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.g5.restaurants.core.domain.entities.Restaurant;
import com.g5.restaurants.core.enums.CuisineType;
import com.g5.restaurants.core.repositories.RestaurantRepository;
import com.g5.restaurants.core.usecases.CreateRestaurantUseCase;
import com.g5.restaurants.core.usecases.SearchRestaurantUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RestaurantUseCaseTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private CreateRestaurantUseCase createRestaurantUseCase;

    @InjectMocks
    private SearchRestaurantUseCase searchRestaurantUseCase;

    private Restaurant criarRestaurante(String id, String nome, CuisineType tipo, String endereco, double latitude, double longitude, LocalTime abertura, LocalTime fechamento, int capacidade) {
        return new Restaurant(id, nome, tipo, endereco, latitude, longitude, abertura, fechamento, capacidade);
    }

    @Test
    void deveSalvarRestaurante() {
        Restaurant restaurant = criarRestaurante("1", "Restaurante A", CuisineType.ITALIANO, "Rua A", 10.0, 20.0,
                LocalTime.of(9, 0), LocalTime.of(22, 0), 50);

        createRestaurantUseCase.execute(restaurant);

        verify(restaurantRepository, times(1)).save(restaurant);
    }

    @Test
    void deveBuscarRestaurantesPorParametros() {
        Restaurant restaurant = criarRestaurante("1", "Restaurante A", CuisineType.ITALIANO, "Rua A", 10.0, 20.0,
                LocalTime.of(9, 0), LocalTime.of(22, 0), 50);
        when(restaurantRepository.findByNameAndCuisineTypeAndLocation("Restaurante A", CuisineType.ITALIANO, 10.0, 20.0))
                .thenReturn(List.of(restaurant));

        List<Restaurant> resultado = searchRestaurantUseCase.execute("Restaurante A", CuisineType.ITALIANO, 10.0, 20.0);

        assertThat(resultado).isNotEmpty();
        assertThat(resultado.get(0).name()).isEqualTo("Restaurante A");
        verify(restaurantRepository, times(1)).findByNameAndCuisineTypeAndLocation("Restaurante A", CuisineType.ITALIANO, 10.0, 20.0);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoEncontrarRestaurante() {
        when(restaurantRepository.findByNameAndCuisineTypeAndLocation("Restaurante Inexistente", CuisineType.BRASILEIRO, 0.0, 0.0))
                .thenReturn(List.of());

        List<Restaurant> resultado = searchRestaurantUseCase.execute("Restaurante Inexistente", CuisineType.BRASILEIRO, 0.0, 0.0);

        assertThat(resultado).isEmpty();
        verify(restaurantRepository, times(1)).findByNameAndCuisineTypeAndLocation("Restaurante Inexistente", CuisineType.BRASILEIRO, 0.0, 0.0);
    }

    @Test
    void deveBuscarTodosOsRestaurantesQuandoNaoHouverParametros() {
        Restaurant restaurant = criarRestaurante("3", "Restaurante D", CuisineType.VEGAN, "Rua D", 18.0, 28.0,
                LocalTime.of(8, 0), LocalTime.of(18, 0), 30);
        when(restaurantRepository.findByNameAndCuisineTypeAndLocation(null, null, null, null))
                .thenReturn(List.of(restaurant));

        List<Restaurant> resultado = searchRestaurantUseCase.execute(null, null, null, null);

        assertThat(resultado).isNotEmpty();
        assertThat(resultado.get(0).name()).isEqualTo("Restaurante D");
        verify(restaurantRepository, times(1)).findByNameAndCuisineTypeAndLocation(null, null, null, null);
    }

    @Test
    void deveLancarExcecaoQuandoEnderecoVazio() {
        // não implementado
    }
    
    @Test
    void deveLancarExcecaoQuandoNomeVazio() {
        // não implementado
    }
    
    @Test
    void deveLancarExcecaoQuandoLatitudeOuLongitudeInvalidos() {
        // não implementado
    }
    
    @Test
    void deveLancarExcecaoQuandoCapacidadeForZeroOuNegativa() {
        // não implementado
    }

    @Test
    void deveLancarExcecaoQuandoHorarioFechamentoAntesDeAbertura() {
        // não implementado
    }

    @Test
    void deveLancarExcecaoQuandoCapacidadeInvalida() {
        // não implementado
    }

    @Test
    void deveLancarExcecao_QuandoParametrosNulosOuInvalidos() {
        // não implementado
    }
}
