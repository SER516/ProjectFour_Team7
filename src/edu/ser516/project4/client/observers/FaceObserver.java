package edu.ser516.project4.client.observers;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import edu.ser516.project4.client.model.Status;
import edu.ser516.project4.client.model.StatusObservable;
import edu.ser516.project4.client.controller.ImageLoader;
import edu.ser516.project4.client.controller.FacialExpressionSolver;
import edu.ser516.project4.client.view.expressive.FacialExpressions;

/** @author Team 7 Observer for face related data */
public class FaceObserver implements Observer {

  private static FaceObserver faceObserverInstance = null;

  /**
   * Get FaceObserver singleton instance
   *
   * @return FaceObserver faceObserverInstance
   */
  public static FaceObserver getInstance() {
    if (faceObserverInstance == null) {
      faceObserverInstance = new FaceObserver();
    }
    return faceObserverInstance;
  }

  /**
   * Updates the facial expression
   *
   * @param observable
   * @param arg
   */
  @Override
  public void update(Observable observable, Object arg) {
    ArrayList<Status> statusContainer = StatusObservable.getObserverInstance().getStatusContainer();
    Status status = statusContainer.get(statusContainer.size() - 1);

    FacialExpressionSolver.LowerFace lowerFace = FacialExpressionSolver.computeLowerFace(status);
    FacialExpressionSolver.UpperFace upperFace = FacialExpressionSolver.computeUpperrFace(status);
    ImageLoader.loadImage(
        FacialExpressions.getPanel(), upperFace.getFileName(), lowerFace.getFileName());
  }
}
