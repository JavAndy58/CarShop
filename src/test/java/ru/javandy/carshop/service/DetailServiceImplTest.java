package ru.javandy.carshop.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.javandy.carshop.dto.DetailDto;
import ru.javandy.carshop.mapper.DetailMapper;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.repository.DetailRepository;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DetailServiceImpl.class, DetailMapper.class})
class DetailServiceImplTest {

    @Autowired
    private DetailService detailService;

    @MockBean
    private DetailRepository detailRepository;

    @MockBean
    private DetailMapper detailMapper;

    @MockBean
    private OrderService orderService;

    @Test
    void saveDetail() {
        Detail testDetail = new Detail(1, "Реле поворота", 1, 100.0, 145.0, " ", false);
        Detail existing = new Detail(1, "Реле поворота", 1, 100.0, 145.0, " ", false);
        DetailDto testDetailDto = new DetailDto("Реле поворота", 1, 100.0, 145.0, " ", false);

        when(detailMapper.toDto(testDetail)).thenReturn(testDetailDto);
        when(detailMapper.toEntity(testDetailDto)).thenReturn(testDetail);
        when(detailRepository.save(testDetail)).thenReturn(existing);

        detailService.saveDetail(testDetailDto);
        verify(detailRepository, times(1)).save(existing);
        verify(detailMapper, times(1)).toEntity(testDetailDto);
        verify(detailMapper, times(1)).toDto(testDetail);
    }

    @Test
    void findByDetailId() {
        int testId = 1;
        Detail testDetail = new Detail(testId, "Реле поворота", 1, 100.0, 145.0, " ", false);
        DetailDto testDetailDto = new DetailDto("Реле поворота", 1, 100.0, 145.0, " ", false);

        when(detailRepository.findById(testId)).thenReturn(Optional.of(testDetail));
        when(detailMapper.toDto(testDetail)).thenReturn(testDetailDto);

        detailService.findByDetailId(testId);
        verify(detailRepository, times(1)).findById(testId);
        verify(detailMapper, times(1)).toDto(testDetail);
    }

    @Test
    void updateDetailId() {
        int testId = 1;
        DetailDto updateDetailDto = new DetailDto("Реле поворота", 1, 100.0, 145.0, " ", false);
        updateDetailDto.setAmount(5);
        Detail detail = new Detail(testId, "Реле поворота", 1, 100.0, 145.0, " ", false);

        when(detailRepository.findById(testId)).thenReturn(Optional.of(detail));
        when(detailMapper.toDto(detail)).thenReturn(updateDetailDto);
        when(detailRepository.save(detail)).thenReturn(detail);

        detailService.updateDetailId(updateDetailDto, testId);
        verify(detailRepository, times(1)).findById(testId);
        verify(detailRepository, times(1)).save(detail);
    }

    @Test
    void deleteByDetailId() {
        int testId = 1;
        DetailDto testDetailDto = new DetailDto("Реле поворота", 1, 100.0, 145.0, " ", false);
        Detail testDetail = new Detail(testId, "Реле поворота", 1, 100.0, 145.0, " ", false);

        when(detailMapper.toDto(testDetail)).thenReturn(testDetailDto);
        when(detailRepository.findById(testId)).thenReturn(Optional.of(testDetail));
        when(orderService.findByDetail(testDetailDto)).thenReturn()



    }
}