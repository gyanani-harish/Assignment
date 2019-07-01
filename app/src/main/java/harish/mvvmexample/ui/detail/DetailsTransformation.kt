package harish.mvvmexample.ui.detail

import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.ChangeTransform
import android.transition.TransitionSet

class DetailsTransformation : TransitionSet() {
    init {
        ordering = ORDERING_TOGETHER;
        addTransition(ChangeBounds())
            .addTransition(ChangeTransform()).addTransition(ChangeImageTransform())
    }
}