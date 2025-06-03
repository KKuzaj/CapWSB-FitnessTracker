package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }


    @Override
    public List<Training> findAllTraining() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> findAllTrainingFinishedAfterTime(Date date) {
        return trainingRepository.findTrainingsFinishedAfter(date);
    }

    @Override
    public List<Training> findAllTrainingByActivityType(ActivityType activityType) {
        return trainingRepository.findTrainingsByActivityType(activityType);
    }

    @Override
    public List<Training> findAllTrainingByUserId(Long userId) {
        return trainingRepository.findTrainingsByUserId(userId);
    }


    @Override
    public Training createTraining(final Training training) {
        log.info("Creating training {}", training);
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training has already DB ID, create is not permitted!");
        }
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(final Long id, final Training training) {
        log.info("Updating Training id: {} with data: {}", id, training);
        if (id == null) {
            throw new IllegalArgumentException("Training does not exist, update is not permitted!");
        }
        training.setId(id);
        return trainingRepository.save(training);
    }
}
