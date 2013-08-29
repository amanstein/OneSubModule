package teambox

/**
 * This filter determines the client type e.g. web browser, telephone etc.
 * Depending on the its type, a client request might be redirected to appropriate controller
 *
 * #### 	Filters and their name
 * #####	`restrictWebBrowsersToVoiceURI`
 * 			If a web browser tries to access voice uri, redirects it to root url
 */
class TeamBoxFilters {

	def filters = {
		// If '/' voice is accessed from web browsers, redirect it to '/'
		restrictWebBrowsersToVoiceURI(uri: '/voice*') {
			before = {
				// Its not a proven method.
				// By logging request header from Zoiper SIP Client, Phono client and browsers, it is observed
				// that 'accept' header is only present in the browsers.
				// However for the time being, its not a critical requirement
//                log.info(request.getHeaderNames().find {it == 'accept'})
				log.info(request.getHeader('accept'))
				if (request.getHeader('accept')) {
					redirect(uri: '/')
				}
				return true
			}
			after = { Map model ->
			}
			afterView = { Exception e ->
			}
		}
	}
}
