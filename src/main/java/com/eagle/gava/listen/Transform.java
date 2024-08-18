package com.eagle.gava.listen;

import com.intellij.psi.PsiMethod;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public abstract class Transform extends SubmissionPublisher<PsiMethod>
        implements Flow.Processor<PsiMethod, PsiMethod> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1L);
    }

    public void requestOne() {
        if (subscription == null) {
            return;
        }
        subscription.request(1L);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("throwable = " + throwable);
    }

    @Override
    public void onComplete() {
        System.out.println("complete");
    }
}
