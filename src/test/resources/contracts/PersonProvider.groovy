

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request{
        method 'GET'
        url('/hello'){
        }
    }
    response {
        status 200
    }
}
