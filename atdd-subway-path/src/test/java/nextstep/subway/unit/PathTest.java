package nextstep.subway.unit;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import nextstep.subway.applicaion.LineService;
import nextstep.subway.applicaion.PathService;
import nextstep.subway.applicaion.StationService;
import nextstep.subway.applicaion.dto.SectionRequest;
import nextstep.subway.applicaion.dto.StationResponse;
import nextstep.subway.domain.Line;
import nextstep.subway.domain.LineRepository;
import nextstep.subway.domain.Section;
import nextstep.subway.domain.Station;
import nextstep.subway.domain.StationRepository;

@ExtendWith(MockitoExtension.class)
public class PathTest {

    long sourceId = 1L;
    long targetId = 3L;

    @Mock
    LineRepository lineRepository;

    @Mock
    StationService stationService;

    @InjectMocks
    LineService lineService;

    @InjectMocks
    PathService pathService;

    //@BeforeEach
    void setUp() {
        // line 조회 -> section 을 가진 line 존재
        // stationId 조회 -> station 객체 반환


    }

    @Test
    void path() {
        Station 강남역 = new Station("강남역");
        Station 역삼역 = new Station("역삼역");
        Station 선릉역 = new Station("선릉역");
        Station 삼성역 = new Station("삼성역");
        Line 이호선 = new Line("이호선", "bg-green");
        int distance = 10;

        Long 강남역Id = 강남역.getId();
        Long 역삼역Id = 역삼역.getId();
        Long 선릉역Id = 선릉역.getId();
        Long 삼성역Id = 삼성역.getId();
        Long 이호선Id = 이호선.getId();
        when(lineRepository.findById(이호선Id)).thenReturn(Optional.of(이호선));
        when(stationService.findById(강남역Id)).thenReturn(강남역);
        when(stationService.findById(역삼역Id)).thenReturn(역삼역);
        // when(stationService.findById(선릉역Id)).thenReturn(선릉역);
        // when(stationService.findById(삼성역Id)).thenReturn(삼성역);

        lineService.addSection(이호선Id, new SectionRequest(강남역Id, 역삼역Id, 1));
        // lineService.addSection(이호선Id, new SectionRequest(역삼역Id, 선릉역Id, 3));
        // lineService.addSection(이호선Id, new SectionRequest(선릉역Id, 삼성역Id, 2));
        when(stationService.findById(선릉역Id)).thenReturn(선릉역);
        lineService.addSection(이호선Id, new SectionRequest(역삼역Id, 선릉역Id, 3));


        // lineService.addSection(이호선Id, new SectionRequest(강남역Id, 역삼역Id, 1));
        // lineService.addSection(이호선Id, new SectionRequest(역삼역Id, 선릉역Id, 3));
        // lineService.addSection(이호선Id, new SectionRequest(선릉역Id, 삼성역Id, 2));
        // lineService.addSection(이호선Id, new SectionRequest(삼성역Id, 강남역Id, 1));

        이호선 = lineService.findLineById(이호선Id);
        List<Section> sections = 이호선.getSections();
        // when(stationService.findById(sourceId)).thenReturn(강남역);

    }



}
